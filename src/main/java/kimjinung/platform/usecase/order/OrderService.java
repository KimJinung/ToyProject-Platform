package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.order.Order;
import kimjinung.platform.dto.order.OrderInfoDTO;

import java.util.List;

public interface OrderService {

    void order(OrderInfoDTO orderInfo);

    List<Order> findAllByUsername(String name);

}
