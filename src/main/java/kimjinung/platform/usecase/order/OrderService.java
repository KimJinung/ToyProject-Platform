package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.order.Order;
import kimjinung.platform.dto.order.OrderInfoDTO;

public interface OrderService {

    void order(OrderInfoDTO orderInfo);

    Order find(Long id);

}
