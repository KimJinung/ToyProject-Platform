package kimjinung.platform.domain.order;


import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.shipment.Shipment;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "shipment_id", unique = true)
    private Shipment shipment;


    public Order() {

    }

    public Order(Member member) {
        this.member = member;
        this.status = OrderStatus.COMPLETE;
        this.shipment = createShipment(this, member.getAddress());
    }

    public Order(Member member, Address address) {
        this.member = member;
        this.status = OrderStatus.COMPLETE;
        this.shipment = createShipment(this, address);
    }

    private Shipment createShipment(Order order, Address address) {
        return new Shipment(order, address);
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }
}
