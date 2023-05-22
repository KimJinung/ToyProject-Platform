package kimjinung.platform.repository.order;

import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.repository.item.ItemRepository;
import kimjinung.platform.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class OrderRepositoryImplTest {

    UUID memberId;
    UUID itemId;
    UUID orderId;

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    private void saveOrder() {
        Optional<Item> item = itemRepository.findById(itemId);
        assert item.isPresent();

        Optional<Member> member = memberRepository.findById(memberId);
        assert member.isPresent();

        Order order = new Order(member.get(), null);
        order.addItem(item.get(), 3);
        order.order();

        orderRepository.save(order);
        orderId = order.getId();
    }

    private void saveItem() {
        Item item = new Item("Item", 100, 10, new ArrayList<>());
        itemRepository.save(item);
        itemId = item.getId();
    }

    private void saveMember() {
        Member member = new Member("Jinung Kim", "0410");
        memberRepository.save(member);
        memberId = member.getId();
    }
    @BeforeEach
    void beforeEach() {
        saveMember();
        saveItem();
        em.flush(); // if not, Occur optimistic Lock
        em.clear();

        saveOrder();
        em.flush();
        em.clear();

    }

    @Test
    void testFindById() {
        Optional<Order> order = orderRepository.findById(orderId);
        assert order.isPresent();

        assertThat(order.get().getMember().getId()).isEqualTo(memberId);
    }

    @Test
    void testFindByMember() {
        Optional<Member> member = memberRepository.findById(memberId);
        assert member.isPresent();


        Optional<List<Order>> order = orderRepository.findByMember(member.get());
        assert order.isPresent();

        assertThat(order.get().size()).isEqualTo(1);
        assertThat(order.get().get(0).getMember().getId()).isEqualTo(memberId);
    }

}