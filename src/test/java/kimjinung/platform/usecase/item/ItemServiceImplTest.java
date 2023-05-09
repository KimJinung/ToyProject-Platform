package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.usecase.category.CategoryService;
import org.assertj.core.api.Assertions;
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

    }

    @Test
    @Transactional
    void testAddStock() {

    }

    @Test
    @Transactional
    void testReduceStock() {

    }
}
