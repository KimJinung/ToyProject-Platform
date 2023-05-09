package kimjinung.platform.infrastructure.repository.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.CategoryItem;
import kimjinung.platform.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    ItemRepository repository;

    private Item item;
    private Category category;
    private CategoryItem categoryItem;

    @BeforeEach
    void beforeEach() {
        item = new Item("Ramen", 1000, 10);
        category = new Category("Food", null);
        categoryItem = new CategoryItem(item, category);

        item.addCategoryItem(categoryItem);
        repository.save(item);
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void testFindByName() {
        List<Item> items = repository.findByName("Ramen");

        assertThat(items.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void testFindById() {
        List<Item> items = repository.findByName("Ramen");
        Item result = items.stream().findFirst().orElse(null);

        assertThat(result).isNotNull();

        Long targetId = result.getId();
        Item foundItem = repository.findById(targetId);

        assertThat(foundItem.getName()).isEqualTo("Ramen");
    }

    @Test
    @Transactional
    void testDelete() {
        List<Item> items = repository.findByName("Ramen");
        Item item = items.stream().findFirst().orElse(null);
        assertThat(item).isNotNull();

        boolean result = repository.delete(item);

        assertThat(result).isTrue();
    }

}