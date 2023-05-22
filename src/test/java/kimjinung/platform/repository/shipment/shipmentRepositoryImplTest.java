package kimjinung.platform.repository.shipment;

import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.shipment.Shipment;
import kimjinung.platform.domain.shipment.ShipmentStatus;
import kimjinung.platform.repository.item.ItemRepository;
import kimjinung.platform.repository.member.MemberRepository;
import kimjinung.platform.repository.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class shipmentRepositoryImplTest {

    UUID shipmentId;
    UUID orderId;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        Member member = new Member("Jinung Kim", "0410", null);
        memberRepository.save(member);

        Item item = new Item("Item", 100, 10, new ArrayList<>());
        itemRepository.save(item);

        Order order = new Order(member, null);
        order.addItem(item, 2);
        order.order();
        orderRepository.save(order);

        shipmentId = order.getShipment().getId();
        orderId = order.getId();
    }


    @Test
    void testFindById() {
        Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
        assert shipment.isPresent();

        assertThat(shipment.get().getStatus()).isEqualTo(ShipmentStatus.PENDING);
    }

    @Test
    void testFindByOrder() {
        Optional<Order> order = orderRepository.findById(orderId);
        assert order.isPresent();

        Optional<Shipment> shipment = shipmentRepository.findByOrder(order.get());
        assert shipment.isPresent();

        assertThat(shipment.get().getId()).isEqualTo(shipmentId);

    }
}