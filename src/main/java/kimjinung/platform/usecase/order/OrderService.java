package kimjinung.platform.usecase.order;

import kimjinung.platform.dto.OrderDTO;

import java.util.UUID;

public interface OrderService {
    UUID order(OrderDTO orderDTO);
}
