package kimjinung.platform.domain.item;


import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Getter
@Entity
public class CategoryLineItem {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_line_item_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
