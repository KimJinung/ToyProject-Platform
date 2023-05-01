package kimjinung.platform.domain.item;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Item {

    @Id @GeneratedValue(strategy = AUTO)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryLineItem> categories = new ArrayList<>();
}
