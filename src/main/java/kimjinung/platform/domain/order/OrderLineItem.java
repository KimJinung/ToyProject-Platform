package kimjinung.platform.domain.order;


import kimjinung.platform.domain.item.Item;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class OrderLineItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int quantity;
    private int orderPrice;
}
