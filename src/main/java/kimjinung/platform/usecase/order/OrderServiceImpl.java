package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.order.OrderItem;
import kimjinung.platform.dto.order.OrderItemDTO;
import kimjinung.platform.dto.order.OrderInfoDTO;
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
        List<OrderItemDTO> items = orderInfo.getItems();

        Member member = memberRepository.findById(memberId);

        String city = orderInfo.getAddressDTO().getCity();
        String street = orderInfo.getAddressDTO().getStreet();
        String postCode = orderInfo.getAddressDTO().getPostCode();
        Address address = new Address(city, street, postCode);
        Order order = new Order(member, address);

        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO item : items) {
            Long itemId = item.getId();
            Integer count = item.getCount();

            Item foundItem = itemRepository.findById(itemId);

            foundItem.reduceStock(count);

            OrderItem orderItem = new OrderItem(order, foundItem, count);

            orderItems.add(orderItem);
        }

        order.addOrderItems(orderItems);

        orderRepository.save(order);

    }

    @Override
    public Order find(Long id) {
        return orderRepository.findById(id);
    }
}
