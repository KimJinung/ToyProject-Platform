package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.usecase.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class ItemServiceImplTest {

    private Item item;

    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;

    @BeforeEach
    void beforeEach() {
        ArrayList<String> child = new ArrayList<>();
        child.add("PC");
        CategoryDTO categoryDTO = new CategoryDTO("Digital", child);
        categoryService.register(categoryDTO);

        Category category = categoryService.find("Digital");
        Long categoryId = category.getId();
        ArrayList<Long> categories = new ArrayList<>(Arrays.asList(categoryId));

        ItemDTO itemDTO = new ItemDTO("CPU", 100000, 10, categories);

        itemService.register(itemDTO);

        List<Item> items = itemService.find("CPU");
        Item result = items.stream().findFirst().orElse(null);

        assertThat(result).isNotNull();

        item = result;
    }

    @Test
    void testFind() {
        List<Item> items = itemService.find("CPU");

        assertThat(items).isNotEmpty();
        assertThat(items.stream().findFirst().orElse(null)).isNotNull();
        assertThat(items.stream().findFirst().orElse(null).getName()).isEqualTo("CPU");
    }

    @Test
    @Transactional
    void testRemove() {
        assertThat(item).isNotNull();

        boolean result = itemService.remove(item.getId());

        assertThat(result).isTrue();

        //When not found item
        boolean resultWhenNotFound = itemService.remove(item.getId());

        assertThat(resultWhenNotFound).isFalse();
    }

    @Test
    @Transactional
    void testAddStock() {
        boolean result = itemService.addStock(item.getId(), 10);
        assertThat(result).isTrue();
    }

    @Test
    @Transactional
    void testReduceStock() {
        boolean result = itemService.reduceStock(item.getId(), 1);
        assertThat(result).isTrue();
    }

    @Test
    @Transactional
    void testReduceStock_ThrowNotEnoughStockQuantityException() {
        boolean result = itemService.reduceStock(item.getId(), 10000000);
        assertThat(result).isFalse();
    }
}
