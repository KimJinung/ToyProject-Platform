package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.order.Order;

public interface OrderRepository {
    void save(Order order);
}
