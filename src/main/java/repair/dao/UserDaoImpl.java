package repair.dao;

import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import repair.model.*;
import repair.util.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/11/2018.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbConnection df = new dbConnection();


    @Override
    public List<AjaxRespAutocomplete> branch(String branchKey) {

            CallableStatement cst = null;
            ResultSet rs = null;
            List<AjaxRespAutocomplete> result =  new ArrayList<>();

            try {
                conn = df.dbConnect();
                cst=conn.prepareCall("{call REPAIR.PCK_USERS.BRANCHES(?,?)}");
                cst.setString(1,branchKey);
                cst.registerOutParameter(2, OracleTypes.CURSOR);
                cst.execute();

                rs = (ResultSet) cst.getObject(2);

                cst.execute();

                while (rs.next()) {
                    AjaxRespAutocomplete branch = new AjaxRespAutocomplete();
                    branch.setValue(rs.getInt("ID"));
                    branch.setLabel(rs.getString("NAME"));
                    result.add(branch);
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
    public Users findByMail(String mail) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Users users =  new Users();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.pck_login.user_by_email(?,?)}");
            cst.setString(1,mail);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {

                users.setUserId(rs.getInt("ID"));
                users.setName(rs.getString("NAME"));
                users.setSurname(rs.getString("SURNAME"));
                users.setEmail(rs.getString("EMAIL"));
                users.setPassword(rs.getString("PASSWORD"));
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

        return users;
    }

    @Override
    public List<String> Actions(int branchId, int userId) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<String> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.pck_login.user_permissions(?,?,?)}");
            cst.setInt(1,branchId);
            cst.setInt(2,userId);
            cst.registerOutParameter(3, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(3);

            cst.execute();

            while (rs.next()) {
                String name;
                name= rs.getString("NAME");
                result.add(name);
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
    public List<Branch> listBranch() {

        CallableStatement cst = null;
        ResultSet rs = null;
        List<Branch> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_LOGIN.BRANCHES(?)}");

            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Branch branch = new Branch();
                branch.setBranchId(rs.getInt("ID"));
                branch.setName(rs.getString("NAME"));
                result.add(branch);
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
    public Integer insertUser(Users users) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Integer id=null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{? = call REPAIR.INSERT_USER(?,?,?,?,?)}");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setString(2,users.getName());
            cst.setString(3,users.getSurname());
            cst.setString(4,users.getEmail());
            cst.setString(5,users.getTel());
            cst.setString(6,users.getPassword());
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
    public boolean insertBranchUser(Integer branchid, Integer userID, Integer roleId) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_BRANCH.INSERT_BRANCH_USERS(?,?,?)}");
            cst.setInt(1,branchid);
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
    public List<Users> users() {

        CallableStatement cst = null;
        ResultSet rs = null;
        List<Users> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_USERS.LIST_USERS(?)}");

            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Users users = new Users();
                users.setUserId(rs.getInt("ID"));
                users.setName(rs.getString("NAME"));
                users.setSurname(rs.getString("SURNAME"));
                users.setEmail(rs.getString("EMAIL"));
                users.setTel(rs.getString("TEL"));
                result.add(users);
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
    public List<OrderResponse> listorders(Integer userId) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<OrderResponse> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_USERS.LSIT_ORDER_BY_USERID(?,?)}");
            cst.setInt(1,userId);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();


            while (rs.next()) {
                OrderResponse orderResponse =new OrderResponse();
                orderResponse.setInfo(rs.getString("description"));
                orderResponse.setOrder_id(rs.getInt("ORDER_ID"));
                orderResponse.setPin(rs.getString("CUSTOMER_PIN"));
                orderResponse.setDevice(rs.getString("DEVICE"));
                Problem problem=new Problem(0,rs.getString("problems"));


                if(result.contains(orderResponse)){
                    System.out.println(result.indexOf(orderResponse));
                    result.get(result.indexOf(orderResponse)).getProblems().add(problem);
                }else {
                    List<Problem> problems= new ArrayList<>();
                    problems.add(problem);
                    orderResponse.setProblems(problems);
                    result.add(orderResponse);
                }


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
    public Users listUserByUserId(Integer userId) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Branch> branchList= new ArrayList<>();
        Users user = new Users();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_USERS.LIST_USER_BY_ID(?,?)}");
            cst.setInt(1,userId);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();
            boolean i=true;
            while (rs.next()) {
               if(i){
                   user.setUserId(rs.getInt("USERID"));
                   user.setName(rs.getString("NAME"));
                   user.setSurname(rs.getString("SURNAME"));
                   user.setEmail(rs.getString("EMAIL"));
                   user.setTel(rs.getString("TEL"));

               }
                 Branch branch=new Branch();
                 branch.setRoleId(rs.getInt("ROLEID"));
                 branch.setRoleName(rs.getString("ROLE_NAME"));
                 branch.setBranchId(rs.getInt("BRANCHID"));
                 branch.setName(rs.getString("BRANCH_NAME"));
                 branch.setBranchUserId(rs.getInt("BUID"));
                 branchList.add(branch);
                 user.setBranches(branchList);
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
        return user;
    }

    @Override
    public boolean deleteBranchUsers(Integer id) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_USERS.DELETE_USER_BRANCH(?)}");
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
