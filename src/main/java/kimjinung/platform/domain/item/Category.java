package kimjinung.platform.domain.item;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    Long id;
    Long parentId;
    String name;

    public Category() {
    }

    public Category(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }
}
