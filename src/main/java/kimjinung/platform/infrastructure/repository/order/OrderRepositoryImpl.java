package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Optional<List<Order>> findAllByUsername(String name) {
        try {
            List<Order> result = (List<Order>) em.createQuery("select o from Order o, Member m where m.name = :name")
                    .setParameter("name", name)
                    .getResultList();
            return Optional.of(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
