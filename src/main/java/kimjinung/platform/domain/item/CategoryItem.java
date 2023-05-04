package kimjinung.platform.domain.item;


import kimjinung.platform.domain.base.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Getter
@Entity
public class CategoryItem extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryItem() {
    }

    public CategoryItem(Item item, Category category) {
        this.item = item;
        this.category = category;
    }

}
