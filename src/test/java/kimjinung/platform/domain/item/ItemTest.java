package kimjinung.platform.domain.item;

import kimjinung.platform.exception.NotEnoughStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class ItemTest {
    Item item;

    @BeforeEach
    void beforeEach() {
        item = new Item("item", 1000, 10, null);
    }

    @Test
    void testReduceStock() {
        boolean result = item.reduceStock(1);
        assertThat(result).isTrue();
        assertThat(item.getStockQuantity()).isEqualTo(9);
    }

    @Test
    void testReduceStock_NotEnoughStockException() {
        org.junit.jupiter.api.Assertions.assertThrows(
                NotEnoughStockException.class,
                () -> item.reduceStock(100)
        );
    }
}