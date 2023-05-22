package kimjinung.platform.repository.order;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    UUID save(Order order);
    Optional<Order> findById(UUID id);
    Optional<List<Order>> findByMember(Member member);

}

