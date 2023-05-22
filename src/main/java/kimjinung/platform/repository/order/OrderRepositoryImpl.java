package kimjinung.platform.repository.order;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
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
        log.info(member.getName());
        List<Order> orders = em.createQuery("select o from Order o where o.member.id = :uuid", Order.class)
                .setParameter("uuid", member.getId())
                .getResultList();
        System.out.println("orders = " + orders);
        return Optional.ofNullable(orders);
    }
}
