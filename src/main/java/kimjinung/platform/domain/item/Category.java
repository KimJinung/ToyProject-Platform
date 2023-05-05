package kimjinung.platform.domain.item;


import kimjinung.platform.domain.base.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Category extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    protected Category() {
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
    public void addChild(Category category) {
        this.child.add(category);
    }

}
