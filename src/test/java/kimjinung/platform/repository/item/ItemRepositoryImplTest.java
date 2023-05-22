package kimjinung.platform.repository.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemRepositoryImplTest {

    Item item;
    List<Category> categories;

    @Autowired
    ItemRepository repository;

    @BeforeEach
    void beforeEach() {
        categories = List.of(new Category(null, "Digital"));
        item = new Item("Item1", 10000, 10, categories);

        repository.save(item);
    }

    @Test
    void testSave() {
        List<Category> category = List.of(new Category(null, "PC"));
        Item macBook = new Item("MacBook", 100000, 2, category);
        UUID result = repository.save(macBook);

        assertThat(result).isInstanceOf(UUID.class);
    }

    @Test
    void testFindById() {
        UUID id = item.getId();

        Item result = repository.findById(id).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Item1");
        assertThat(result.getPrice()).isEqualTo(10000);
        assertThat(result.getStockQuantity()).isEqualTo(10);

    }

    @Test
    void testFindByName() {
        List<Item> result = repository.findByName("Item1").orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Item1");
        assertThat(result.get(0).getPrice()).isEqualTo(10000);
        assertThat(result.get(0).getStockQuantity()).isEqualTo(10);

    }

}