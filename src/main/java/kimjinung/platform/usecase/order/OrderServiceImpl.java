package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.domain.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public boolean order(Member member, Item... item) {
        /*
        전달 받은 item을 가지고 orderItem으로 변환해서 넘겨주어야한다.
        해당 역할은 item이 해줘야할듯?
         */

//        Order order = new Order(member, );
        return false;
    }
}
