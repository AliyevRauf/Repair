package repair.dao;

import repair.model.AjaxRespAutocomplete;
import repair.model.Order;
import repair.model.OrderResponse;
import repair.model.Problem;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
public interface OrderDao {

    List<Problem> problems();

    List<AjaxRespAutocomplete> device(String deviceKey);

    Integer insertOrder(Order order);

    boolean insertOrdrDetail(Integer orderId,int problem);

    List<OrderResponse> listorders();

    OrderResponse orderResponseById(int id);

    boolean editOrder(Order order);

    boolean deleteOrderDetails(int id);
}
