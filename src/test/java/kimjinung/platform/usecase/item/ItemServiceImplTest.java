package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.CategoryItem;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.usecase.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @BeforeEach
    void beforeEach() {
        ArrayList<String> child = new ArrayList<>();
        child.add("PC");

        CategoryDTO category = new CategoryDTO("Digital", child);

        categoryService.add(category);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void foundTest() {
        ArrayList<Long> categories = new ArrayList<>();

        categories.add(2L);

        ItemDTO itemDTO = new ItemDTO("MacBook", 100000, 100, categories);

        itemService.register(itemDTO);

        List<Item> items = itemService.find("MacBook");

        assertThat(items.size()).isEqualTo(1);

        for (Item item : items) {
            assertThat(item.getName()).isEqualTo("MacBook");
            assertThat(item.getPrice()).isEqualTo(100000);
            assertThat(item.getStockQuantity()).isEqualTo(100);

            for (CategoryItem category : item.getCategories()) {
                System.out.println("category = " + category.getCategory().getName());
                assertThat(category.getCategory().getName()).isEqualTo("Digital");
            }

        }
    }
}