package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.order.Order;

import java.util.List;
import java.util.Optional;


public interface OrderRepository {
    void save(Order order);

    Order findById(Long id);

    Optional<List<Order>> findAllByUsername(String name);
}
