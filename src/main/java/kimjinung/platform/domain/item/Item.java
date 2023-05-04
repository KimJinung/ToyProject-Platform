package kimjinung.platform.domain.item;


import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.exception.NotEnoughStockEx;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = AUTO)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private Long price;

    private Integer stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categories = new ArrayList<>();

    protected Item() {
    }

    public Item(String name, Long price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void reduceStock(int stockQuantity) {
        int remainingStock = this.stockQuantity - stockQuantity;

        if (remainingStock < 0) {
            throw new NotEnoughStockEx("Can't reduce to less than 0 in stock");
        }

        this.stockQuantity = remainingStock;
    }
}
