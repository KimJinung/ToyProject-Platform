package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.item.Item;
import kimjinung.platform.domain.member.Member;

public interface OrderService {

    boolean order(Member member, Item... item);
}
