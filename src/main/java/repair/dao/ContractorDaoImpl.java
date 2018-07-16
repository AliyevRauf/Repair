package repair.dao;

import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import repair.model.Contractor;
import repair.model.Role;
import repair.util.dbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rauf on 27/06/2018.
 */
@Repository
public class ContractorDaoImpl implements ContractorDao{
    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbConnection df = new dbConnection();

    @Override
    public boolean addContractor(Contractor contractor) {
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_CONTRACTOR.ADD_CONTRACTOR(?,?,?)}");
            cst.setString(1,contractor.getCompanyName());
            cst.setString(2,contractor.getAddress());
            cst.setString(3,contractor.getPhone());
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
    public List<Contractor> listContractor() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Contractor> listResult = new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_CONTRACTOR.LIST_CONTRACTOR(?)}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Contractor contractor = new Contractor();
                contractor.setId(rs.getInt("ID"));
                contractor.setCompanyName(rs.getString("NAME"));
                contractor.setAddress(rs.getString("ADDRESS"));
                contractor.setPhone(rs.getString("TELEFON"));
                listResult.add(contractor);
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
    public Contractor listbyId(int id) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Contractor listRoleById = new Contractor();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_CONTRACTOR.LIST_BY_ID(?,?)}");
            cst.setInt(1,id);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while(rs.next()){
                listRoleById.setId(rs.getInt("ID"));
                listRoleById.setCompanyName(rs.getString("NAME"));
                listRoleById.setAddress(rs.getString("ADDRESS"));
                listRoleById.setPhone(rs.getString("TELEFON"));
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
    public boolean editContractor(Contractor contractor) {
            CallableStatement cst = null;
            ResultSet rs = null;
            try {
                conn = df.dbConnect();
                cst=conn.prepareCall("{call REPAIR.PCK_CONTRACTOR.EDIT_CONTRACTOR(?,?,?,?)}");
                cst.setInt(1,contractor.getId());
                cst.setString(2,contractor.getCompanyName());
                cst.setString(3,contractor.getAddress());
                cst.setString(4,contractor.getPhone());
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
