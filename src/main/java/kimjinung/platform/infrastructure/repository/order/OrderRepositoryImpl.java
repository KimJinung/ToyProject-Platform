package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        Order order = em.find(Order.class, id);

        return order;
    }
}
