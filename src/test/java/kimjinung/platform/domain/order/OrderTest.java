package kimjinung.platform.domain.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.shipment.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTest {

    private Order order;
    private Member member;
    private Address address;

    @BeforeEach
    public void beforeEach() {
        member = new Member("JinungKim", "0410", address);
        address = new Address("KY", "SW", "95");
        order = new Order(member, address);
    }
    @Test
    @org.junit.jupiter.api.Order(1)
    void testCreateShipment() {
        Shipment shipment = order.getShipment();
        assertThat(shipment.getOrder()).isEqualTo(order);
        assertThat(shipment.getOrder().getMember()).isEqualTo(member);
        assertThat(shipment.getAddress()).isEqualTo(address);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETE);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testCancelOrder() {
        order.cancelOrder();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

}