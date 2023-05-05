package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.dto.member.AddressDTO;
import kimjinung.platform.dto.member.MemberDTO;
import kimjinung.platform.dto.order.OrderInfoDTO;
import kimjinung.platform.dto.order.OrderItemDTO;
import kimjinung.platform.infrastructure.repository.item.ItemRepository;
import kimjinung.platform.usecase.category.CategoryService;
import kimjinung.platform.usecase.item.ItemService;
import kimjinung.platform.usecase.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        categoryService.add(categoryDTO);

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
    @Rollback(value = false)
    void orderTest() {
        OrderItemDTO orderItem1 = new OrderItemDTO(4L, 2);
        OrderItemDTO orderItem2 = new OrderItemDTO(6L, 10);

        ArrayList<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        AddressDTO address = new AddressDTO("KY", "SW", "95");

        OrderInfoDTO orderInfo = new OrderInfoDTO(1L, address, orderItems);

        orderService.order(orderInfo);

        Order order = orderService.find(8L);

        Assertions.assertThat(order.getMember().getName()).isEqualTo("JinungKim");

    }

}