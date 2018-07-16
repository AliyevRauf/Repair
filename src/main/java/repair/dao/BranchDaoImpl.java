package repair.dao;

import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import repair.model.*;
import repair.util.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Repository
public class BranchDaoImpl implements BranchDao {

    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbConnection df = new dbConnection();


    @Override
    public List<AjaxRespAutocomplete> users(String userKey) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<AjaxRespAutocomplete> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.USERS_BY_KEY(?,?)}");
            cst.setString(1,userKey);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {
                AjaxRespAutocomplete user = new AjaxRespAutocomplete();
                user.setValue(rs.getInt("ID"));
                user.setLabel(rs.getString("FULLNAME"));
                result.add(user);
            }

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

        return result;
    }

    @Override
    public List<AjaxRespAutocomplete> city(String cityKey) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<AjaxRespAutocomplete> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.CITY_BY_KEY(?,?)}");
            cst.setString(1,cityKey);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {
                AjaxRespAutocomplete city = new AjaxRespAutocomplete();
                city.setValue(rs.getInt("ID"));
                city.setLabel(rs.getString("NAME"));
                result.add(city);
            }

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

        return result;
    }

    @Override
    public List<AjaxRespAutocomplete> region(String regionKey) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<AjaxRespAutocomplete> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.REGION_BY_KEY(?,?)}");
            cst.setString(1,regionKey);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {
                AjaxRespAutocomplete city = new AjaxRespAutocomplete();
                city.setValue(rs.getInt("ID"));
                city.setLabel(rs.getString("NAME"));
                result.add(city);
            }

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

        return result;
    }

    @Override
    public Integer insertBranch(Branch branch) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Integer id=null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{? = call REPAIR.INSERT_BRANCH(?,?,?,?,?)}");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setString(2,branch.getName());
            cst.setString(3,branch.getEmail());
            cst.setInt(4,branch.getCityId());
            cst.setInt(5,branch.getRegionId());
            cst.setString(6,branch.getAddress());
            cst.execute();
            id=cst.getInt(1);

            System.out.println("row inserted");
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

            } catch (SQLException e) {
                System.out.println("not closed");
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public boolean insertBranchUser(Integer id, Integer userID, Integer roleId) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_BRANCH.INSERT_BRANCH_USERS(?,?,?)}");
            cst.setInt(1,id);
            cst.setInt(2,userID);
            cst.setInt(3,roleId);
            cst.execute();

            System.out.println("row inserted");
            //conn.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return false;

        }finally{
            try{
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
    public List<Branch> listBranches() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Branch> listResult = new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.LIST_BRANCHES(?)}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
              Branch branch = new Branch();
                branch.setBranchId(rs.getInt("ID"));
                branch.setName(rs.getString("NAME"));
                branch.setLocation(rs.getString("LOCATIONS"));
                branch.setEmail(rs.getString("EMAIL"));
                branch.setAddress(rs.getString("ADDRESS"));
                listResult.add(branch);
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
    public List<Users> listUsersByBranchId(Integer id) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Users> listResult = new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.lIST_BRANCH_USERS(?,?)}");
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.setInt(1,id);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {
                Users users = new Users();
                users.setUserId(rs.getInt("ID"));
                users.setName(rs.getString("NAME"));
                users.setSurname(rs.getString("SURNAME"));
                users.setEmail(rs.getString("EMAIL"));
                users.setTel(rs.getString("TEL"));
                users.setRoleId(rs.getInt("ROLE_ID"));
                users.setRoleName(rs.getString("ROLE_name"));

                listResult.add(users);
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
    public Branch listBranchById(Integer id) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Branch branch = new Branch();
        List<Users> usersList = new ArrayList<>();
        branch.setUsers(usersList);
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.LIST_BRANCH_BY_ID(?,?)}");
            cst.setInt(1,id);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();
            boolean i=true;
            while (rs.next()) {


                if (i) {
                    branch.setBranchId(rs.getInt("BRANCH_ID"));
                    branch.setName(rs.getString("BRANCH_NAME"));
                    branch.setEmail(rs.getString("EMAIL"));
                    branch.setCityId(rs.getInt("CITY_ID"));
                    branch.setCityName(rs.getString("CITY_NAME"));
                    branch.setRegionId(rs.getInt("REGION_ID"));
                    branch.setRegionName(rs.getString("REGION_NAME"));
                    branch.setAddress(rs.getString("ADDRESS"));
                }

                Users user = new Users();
                user.setUserId(rs.getInt("USER_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setSurname(rs.getString("SURNAME"));
                user.setRoleId(rs.getInt("ROLE_ID"));
                user.setRoleName(rs.getString("ROLE_NAME"));
                user.setBranchUserId(rs.getInt("BU_ID"));
                branch.getUsers().add(user);


              i=false;
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


        return branch;
    }

    @Override
    public List<Users> listRoles() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Users> roleList= new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_BRANCH.lIST_ROLES(?)}");

            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Users user = new Users();
                user.setRoleId(rs.getInt("ID"));
                user.setRoleName(rs.getString("NAME"));
                roleList.add(user);
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
        return roleList;
    }

    @Override
    public boolean deleteBranchUsers(Integer id) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_BRANCH.DELETE_BRANCH_USERS(?)}");
            cst.setInt(1,id);
            cst.execute();

            System.out.println("row deleted");
            //conn.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return false;

        }finally{
            try{
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
    public boolean updateBranchUsers(Integer buId, Integer roleId) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_BRANCH.UPDATE_BRANCH_USERS(?,?)}");
            cst.setInt(1,buId);
            cst.setInt(2,roleId);
            cst.execute();

            System.out.println("row updated");
            //conn.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("Error while save Role error=" + ex.toString());
            return false;

        }finally{
            try{
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
