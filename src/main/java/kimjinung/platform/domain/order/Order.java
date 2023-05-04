package kimjinung.platform.domain.order;


import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.shipment.Shipment;
import lombok.Getter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private String id;

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

    public Order(Member member, OrderItem... orderItems) { // 이 orderItems 넘기는 게 서비스 레이어에서 해주어야 할까.. 아닐텐데
        this.member = member;
        this.orderItems = Arrays.asList(orderItems);
        this.shipment = generateShipmentInfo(this, member.getAddress());
    }


    private Shipment generateShipmentInfo(Order order, Address address) {
        return new Shipment(order, address);
    }
}
