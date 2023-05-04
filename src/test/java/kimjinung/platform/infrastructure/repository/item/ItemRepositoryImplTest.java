package kimjinung.platform.infrastructure.repository.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.CategoryItem;
import kimjinung.platform.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Rollback(value = false)
@Transactional(readOnly = true)
@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void testSaveAndFindByName() {
        Item item = new Item("Ramen", 1000L, 10);

        Category category = new Category("Food");

        CategoryItem categoryItem = new CategoryItem(item, category);

        item.addCategories(Arrays.asList(categoryItem));

        itemRepository.save(item);

        Item foundItem = itemRepository.findByName("Ramen");

        Assertions.assertThat(foundItem.getName()).isEqualTo(item.getName());
    }

    @Test
    @Transactional
    public void testFindById() {
        Item item = new Item("Ramen", 1000L, 10);

        Category category = new Category("Food");

        CategoryItem categoryItem = new CategoryItem(item, category);

        item.addCategories(Arrays.asList(categoryItem));

        itemRepository.save(item);

        Item foundItem = itemRepository.findById(1L);

        Assertions.assertThat(foundItem.getId()).isEqualTo(1L);
        Assertions.assertThat(foundItem.getName()).isEqualTo("Ramen");
    }

    // TODO Write test case for adding category methods
}