package kimjinung.platform.repository.order;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final EntityManager em;
    @Override
    public UUID save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public Optional<Order> findById(UUID id) {
        Order order = em.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<List<Order>> findByMember(Member member) {
        List<Order> orders = em.createQuery("select o from Order o where o.member.id = :uuid", Order.class)
                .setParameter("uuid", member.getId())
                .getResultList();

        return Optional.ofNullable(orders);
    }
}
