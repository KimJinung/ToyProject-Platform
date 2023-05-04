package kimjinung.platform.domain.order;


import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.domain.item.Item;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

    private Long orderPrice;

    public OrderItem() {
    }

    public OrderItem(Order order, Item item, Integer quantity) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.orderPrice = item.getPrice() * quantity;
    }
}
