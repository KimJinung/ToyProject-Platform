package kimjinung.platform.domain.item;


import kimjinung.platform.domain.base.BaseEntity;
import lombok.Getter;

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
}
