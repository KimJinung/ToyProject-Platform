package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.order.OrderItem;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.dto.member.AddressDTO;
import kimjinung.platform.dto.member.MemberDTO;
import kimjinung.platform.dto.order.OrderInfoDTO;
import kimjinung.platform.dto.order.OrderItemDTO;
import kimjinung.platform.usecase.category.CategoryService;
import kimjinung.platform.usecase.item.ItemService;
import kimjinung.platform.usecase.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;

    @BeforeEach
    void beforeEach() {
        AddressDTO address = new AddressDTO("KY", "SW", "95");
        MemberDTO member = new MemberDTO("JinungKim", "0410", address);
        memberService.join(member);

        ArrayList<String> child = new ArrayList<>();
        child.add("PC");
        CategoryDTO categoryDTO = new CategoryDTO("Digital", child);
        categoryService.register(categoryDTO);

        ArrayList<Long> categories = new ArrayList<>();

        Category category = categoryService.find("Digital");
        categories.add(category.getId());
        ItemDTO itemDTO = new ItemDTO("MacBook", 100000, 100, categories);
        itemService.register(itemDTO);
        itemService.register(itemDTO);

        List<Item> result = itemService.find("MacBook");

        for (Item item : result) {
            System.out.println("item = " + item.getName());
        }
    }

    @Test
    @Transactional
    void testOrder() {
        Member member = memberService.search("JinungKim");
        Item item = itemService.find("MacBook").stream().findFirst().orElse(null);

        assertThat(item).isNotNull();

        AddressDTO addressDTO = new AddressDTO("KY", "SW", "95");
        ArrayList<OrderItemDTO> orderItems = new ArrayList<>() {{
            add(new OrderItemDTO(item.getId(), 10));
        }};

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO(member.getId(), addressDTO, orderItems);

        orderService.order(orderInfoDTO);
    }

    @Test
    @Transactional
    void testFindAllByUsername() {
        Member member = memberService.search("JinungKim");
        Item item = itemService.find("MacBook").stream().findFirst().orElse(null);

        assertThat(item).isNotNull();

        AddressDTO addressDTO = new AddressDTO("KY", "SW", "95");
        ArrayList<OrderItemDTO> orderItems = new ArrayList<>() {{
            add(new OrderItemDTO(item.getId(), 10));
        }};

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO(member.getId(), addressDTO, orderItems);

        orderService.order(orderInfoDTO);

        List<Order> orders = orderService.findAllByUsername("JinungKim");

        assertThat(orders).isNotEmpty();

        Order order = orders.stream().findFirst().orElse(null);
        assertThat(order).isNotNull();

        assertThat(order.getMember().getName()).isEqualTo("JinungKim");

        OrderItem orderItem = order.getOrderItems().stream().findFirst().orElse(null);
        System.out.println("orderItem = " + orderItem);
        String name = orderItem.getItem().getName();

        assertThat(name).isEqualTo("MacBook");
    }

}