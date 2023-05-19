package kimjinung.platform.repository.order;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    boolean save(Order order);
    Order findById(UUID id);
    List<Order> findByMember(Member member);

}

