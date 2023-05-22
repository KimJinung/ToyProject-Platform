package kimjinung.platform.domain.order;


import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.shipment.Shipment;
import kimjinung.platform.exception.NotEnoughStockException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Getter
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private final List<OrderItem> items = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = PERSIST)
    private Shipment shipment;

    public Order() {
    }

    public Order(Member member) {
        this.member = member;
    }

    public void addItem(Item item, int quantity) {
        OrderItem orderItem = new OrderItem(this, item, quantity);
        this.items.add(orderItem);
    }

    public void order(Address address) throws NotEnoughStockException {
        items.forEach(orderItem -> {
            Integer quantity = orderItem.getQuantity();
            orderItem.getItem().reduceStock(quantity);
        });

        this.shipment = new Shipment(this, address);
    }

}
