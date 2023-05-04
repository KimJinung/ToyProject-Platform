package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.order.OrderItem;
import kimjinung.platform.dto.ItemDTO;
import kimjinung.platform.dto.OrderInfoDTO;
import kimjinung.platform.infrastructure.repository.item.ItemRepository;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import kimjinung.platform.infrastructure.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Override
    public void order(OrderInfoDTO orderInfo) {
        Long memberId = orderInfo.getMemberId();
        List<ItemDTO> items = orderInfo.getItems();

        Member member = memberRepository.findById(memberId);
        Address address = new Address(orderInfo.getCity(), orderInfo.getStreet(), orderInfo.getZipCode());
        Order order = new Order(member, address);
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for (ItemDTO item : items) {
            Long itemId = item.getId();
            Integer count = item.getCount();

            Item foundItem = itemRepository.findById(itemId);

            OrderItem orderItem = new OrderItem(order, foundItem, count);

            orderItems.add(orderItem);
        }

        order.addOrderItems(orderItems);

        orderRepository.save(order);

    }
}
