package kimjinung.platform.domain.item;


import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.exception.NotEnoughStockException;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = AUTO)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;

    @OneToMany(mappedBy = "item", cascade = PERSIST)
    private final List<CategoryItem> categories = new ArrayList<>();

    protected Item() {
    }

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addCategoryItem(CategoryItem categoryItem) {
        this.categories.add(categoryItem);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void reduceStock(int stockQuantity) {
        int remainingStock = this.stockQuantity - stockQuantity;

        if (remainingStock < 0) {
            throw new NotEnoughStockException("Can't reduce to less than 0 in stock");
        }

        this.stockQuantity = remainingStock;
    }

}
