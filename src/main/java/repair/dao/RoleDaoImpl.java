package repair.dao;

import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import repair.model.Role;
import repair.util.dbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Rauf on 17/06/2018.
 */
@Repository
public class RoleDaoImpl implements RoleDao{

    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbConnection df = new dbConnection();


    @Override
    public boolean createNewRole(Role role){
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.ROLES1.CREATE_ROLE1(?,?,?,?,?)}");
            cst.setString(1,role.getRoleName());
            cst.setString(2,role.getCreatUser());
            cst.setString(3,role.getCreateBranch());
            cst.setString(4,role.getCreateOrder());
            cst.setString(5,role.getUsersOrders());
            cst.execute();
            System.out.println("row inserted");
            //conn.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return false;
        }
        finally {
            try {
                conn.close();
                cst.close();

            } catch (SQLException e) {
                System.out.println("not closed");
                e.printStackTrace();
            }
        }
        return true;

    }

    @Override
    public List<Role> listRole() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Role> listResult = new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.ROLES1.lIST_ROLES(?)}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("ID"));
                role.setRoleName(rs.getString("role_name"));
                role.setCreatUser(rs.getString("create_user"));
                role.setCreateBranch(rs.getString("create_branch"));
                role.setCreateOrder(rs.getString("create_order"));
                role.setUsersOrders(rs.getString("users_order"));
                listResult.add(role);
            }

            //conn.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return null;
        }
        finally {
            try {
                conn.close();
                cst.close();
                rs.close();

            } catch (SQLException e) {
                System.out.println("not closed");
                e.printStackTrace();
            }
        }
        return listResult;

    }

    @Override
    public Role listRoleById(int id) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Role listRoleById = new Role();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.ROLES1.EDIT_ROLE(?,?)}");
            cst.setInt(1,id);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while(rs.next()){
                listRoleById.setId(rs.getInt("ID"));
                listRoleById.setRoleName(rs.getString("role_name"));
                listRoleById.setCreatUser(rs.getString("create_user"));
                listRoleById.setCreateBranch(rs.getString("create_branch"));
                listRoleById.setCreateOrder(rs.getString("create_order"));
                listRoleById.setUsersOrders(rs.getString("users_order"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return null;
        } finally {
            try {
                conn.close();
                cst.close();
                rs.close();

            } catch (SQLException e) {
                System.out.println("not closed");
                e.printStackTrace();
            }
        }


        return listRoleById;
    }

    @Override
    public boolean editRole(Role role) {
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.ROLES1.UPDATE_ROLE(?,?,?,?,?,?)}");
            cst.setInt(1,role.getId());
            cst.setString(2,role.getRoleName());
            cst.setString(3,role.getCreatUser());
            cst.setString(4,role.getCreateBranch());
            cst.setString(5,role.getCreateOrder());
            cst.setString(6,role.getUsersOrders());
            cst.execute();
            System.out.println("row updated");
            //conn.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return false;
        }
        finally {
            try {
                conn.close();
                cst.close();

            } catch (SQLException e) {
                System.out.println("not closed");
                e.printStackTrace();
            }
        }
        return true;

    }
}
