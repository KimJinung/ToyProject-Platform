package kimjinung.platform.domain.order;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.shipment.ShipmentStatus;
import kimjinung.platform.exception.NotEnoughStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    Member member;
    Address address;
    List<Item> items;
    List<Category> categories;

    @BeforeEach

    void beforeEach() {
        member = new Member("Jinung Kim", "0410");
        order = new Order(member);
        address = new Address("KY", "SW", "95");
        categories = new ArrayList<>();
    }

    @Test
    void testAddItem() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    String itemName = String.format("item%s", i);
                    Item item = new Item(itemName, i, i, categories);
                    order.addItem(item, i);
                });

        assertThat(order.getItems().size()).isEqualTo(10);


        IntStream.range(0, 10)
                .forEach(i -> {
                    OrderItem item = order.getItems().get(i);
                    assertThat(item.getItem().getStockQuantity()).isEqualTo(i + 1);
                });
    }

    @Test
    void testOrder() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    String itemName = String.format("item%s", i);
                    Item item = new Item(itemName, i, i, categories);
                    order.addItem(item, i);
                });

        order.order(address);

        assertThat(order.getShipment().getStatus()).isEqualTo(ShipmentStatus.PENDING);
    }

    @Test
    void testOrder_NotEnoughStockException() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    String itemName = String.format("item%s", i);
                    Item item = new Item(itemName, i, i, categories);
                    order.addItem(item, i * i);
                });

        assertThrows(
                NotEnoughStockException.class,
                () -> order.order(address)
        );
    }
}