package repair.service;

import org.springframework.stereotype.Component;
import repair.model.AjaxRespAutocomplete;
import repair.model.Order;
import repair.model.OrderResponse;
import repair.model.Problem;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Component
public interface OrderService {

    List<Problem> Problems();

    List<AjaxRespAutocomplete> device(String deviceKey);

    boolean insertOrder(Order order);

    List<OrderResponse> listorders();

    OrderResponse orderResponseById(int id);

    boolean editOrder(Order order);





}
