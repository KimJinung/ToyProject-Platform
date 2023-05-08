package kimjinung.platform.domain.item;

import kimjinung.platform.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testAddCategoryItem() {
        Item item = new Item("CPU", 1000, 10);
        Category category = new Category("Digital");
        CategoryItem categoryItem = new CategoryItem(item, category);

        item.addCategoryItem(categoryItem);

        assertThat(item.getCategories().size()).isEqualTo(1);
        assertThat(item.getCategories().stream()
                .filter(c -> c.getCategory().getName().equals("Digital"))
                .findFirst()).isNotEmpty();
    }

    @Test
    void testAddStock() {
        Item item = new Item("CPU", 100, 10);
        item.addStock(10);

        assertThat(item.getStockQuantity()).isEqualTo(20);
    }

    @Test
    void testReduceStock() {
        Item item = new Item("CPU", 1000, 10);
        item.reduceStock(2);

        assertThat(item.getStockQuantity()).isEqualTo(8);
    }

    @Test
    void testReduceStock_ThrowNotEnoughStock() {
        Item item = new Item("CPU", 1000, 10);

        assertThrows(NotEnoughStockException.class,
                () -> item.reduceStock(100));
    }

}