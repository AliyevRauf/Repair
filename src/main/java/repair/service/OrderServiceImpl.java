package repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair.dao.OrderDao;
import repair.model.AjaxRespAutocomplete;
import repair.model.Order;
import repair.model.OrderResponse;
import repair.model.Problem;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Problem> Problems() {
        return orderDao.problems();
    }

    @Override
    public List<AjaxRespAutocomplete> device(String deviceKey) {
        return orderDao.device(deviceKey);
    }

    @Override
    public boolean insertOrder(Order order) {
        Integer orderId;
        if(order.getDevice_id() == -1){
            return false;
        }else {
             orderId = orderDao.insertOrder(order);
            if (orderId == null) {
                return false;
            }
        }
        for (int i : order.getProblems()) {

            orderDao.insertOrdrDetail(orderId,i);
        }
        return true;
    }

    @Override
    public List<OrderResponse> listorders() {
        return orderDao.listorders();
    }

    @Override
    public OrderResponse orderResponseById(int id) {
        return orderDao.orderResponseById(id);
    }

    @Override
    public boolean editOrder(Order order) {

        orderDao.editOrder(order);

        boolean result = orderDao.deleteOrderDetails(order.getId());

        if(result){
            for (int i : order.getProblems()) {

                orderDao.insertOrdrDetail(order.getId(),i);
            }
            return true;
        }

        return false;
    }
}
