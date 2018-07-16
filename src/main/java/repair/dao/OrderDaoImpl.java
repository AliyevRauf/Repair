package repair.dao;

import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import repair.model.AjaxRespAutocomplete;
import repair.model.Order;
import repair.model.OrderResponse;
import repair.model.Problem;
import repair.util.dbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbConnection df = new dbConnection();

    @Override
    public List<Problem> problems() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Problem> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_ORDER.PROBLEMS(?)}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

            cst.execute();

            while (rs.next()) {
                Problem problem = new Problem();
                problem.setId(rs.getInt("ID"));
                problem.setName(rs.getString("NAME"));
                result.add(problem);
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
    public List<AjaxRespAutocomplete> device(String deviceKey) {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<AjaxRespAutocomplete> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_ORDER.DEVICES(?,?)}");
            cst.setString(1,deviceKey);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            while (rs.next()) {
                AjaxRespAutocomplete device = new AjaxRespAutocomplete();
                device.setValue(rs.getInt("ID"));
                device.setLabel(rs.getString("DEVICE"));
                result.add(device);
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
    public Integer insertOrder(Order order) {
        CallableStatement cst = null;
        ResultSet rs = null;
        Integer id=null;
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{? = call REPAIR.INSERT_ORDER(?,?,?,?,?)}");
            cst.registerOutParameter(1, Types.INTEGER);
            cst.setString(2,order.getPin());
            cst.setInt(3,order.getDevice_id());
            cst.setString(4,order.getInfo());
            cst.setInt(5,order.getUserId());
            cst.setInt(6,order.getBranchId());
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
    public boolean insertOrdrDetail(Integer orderId, int problem) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_ORDER.INSERT_ORDER_DETAIL(?,?)}");
            cst.setInt(1,orderId);
            cst.setInt(2,problem);
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
    public List<OrderResponse> listorders() {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<OrderResponse> result =  new ArrayList<>();

        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_ORDER.LIST_ORDERS(?)}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(1);

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
    public OrderResponse orderResponseById(int id) {
        CallableStatement cst = null;
        ResultSet rs = null;
        OrderResponse orderResponse =new OrderResponse();
        List<Problem> problems= new ArrayList<>();
        try {
            conn = df.dbConnect();
            cst=conn.prepareCall("{call REPAIR.PCK_ORDER.lIST_ORDER_ID(?,?)}");
            cst.setInt(1,id);
            cst.registerOutParameter(2, OracleTypes.CURSOR);
            cst.execute();

            rs = (ResultSet) cst.getObject(2);

            cst.execute();

            int count=0;
            while (rs.next()) {
                if(count==0) {
                    orderResponse.setInfo(rs.getString("description"));
                    orderResponse.setOrder_id(rs.getInt("ORDERID"));
                    orderResponse.setPin(rs.getString("CUSTOMER_PIN"));
                    orderResponse.setDevice(rs.getString("DEVICE"));
                    orderResponse.setDeviceId(rs.getInt("DEVICE_ID"));
                    count++;
                }
                Problem problem=new Problem(rs.getInt("PROBLEM_ID"),"");
                problems.add(problem);
                orderResponse.setProblems(problems);

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



        return orderResponse;
    }

    @Override
    public boolean editOrder(Order order) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_ORDER.UPDATE_ORDER(?,?,?,?)}");
            cst.setInt(1,order.getId());
            cst.setString(2,order.getPin());
            cst.setInt(3,order.getDevice_id());
            cst.setString(4,order.getInfo());
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

    @Override
    public boolean deleteOrderDetails(int id) {
        CallableStatement cst=null;
        ResultSet rs=null;
        try{
            conn = df.dbConnect();
            cst=conn.prepareCall("{ call REPAIR.PCK_ORDER.DELETE_ORDER_DETAIL(?)}");
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
}
