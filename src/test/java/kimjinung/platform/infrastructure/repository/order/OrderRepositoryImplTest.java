package kimjinung.platform.infrastructure.repository.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class OrderRepositoryImplTest {

    private Address address;
    private Member member;
    private Order order;

    @Autowired
    EntityManager em;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;


    @BeforeEach
    void beforeEach() {
        address = new Address("KY", "SW", "95");
        member = new Member("JinungKim", "0410", address);
        order = new Order(member);

        memberRepository.save(member);
        orderRepository.save(order);

        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testFindById() {
        Long id = order.getId();
        Order result = orderRepository.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getMember().getName()).isEqualTo("JinungKim");
    }

}