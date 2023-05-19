package kimjinung.platform.domain.item;

import kimjinung.platform.exception.NotEnoughStockException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "item_id")
    private UUID id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    @OneToMany(mappedBy = "item")
    private List<ItemCategory> categories = new ArrayList<>();

    public Item() {

    }

    public Item(String name, Integer price, Integer stockQuantity, List<ItemCategory> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }

    public boolean addStock(int quantity) {
        this.price += quantity;
        return true;
    }

    public boolean reduceStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("Not Enough Stock");
        }

        this.stockQuantity = restStock;
        return true;
    }

}
