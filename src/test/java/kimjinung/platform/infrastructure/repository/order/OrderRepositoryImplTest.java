package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    void orderTest() {
        Address address = new Address("KY", "SW", "96");
        Member member = new Member("JinungKim", "0410", address);

        Order order = new Order(member);

        orderRepository.save(order);

        Order foundOrder = orderRepository.findById(1L);

        assertThat(foundOrder.getMember().getName()).isEqualTo("JinungKim");
    }

}