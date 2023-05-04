package kimjinung.platform.usecase.order;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.infrastructure.repository.item.ItemRepository;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void beforeEach() {
        Member member = new Member();

    }

    @Test
    @Transactional
    void orderTest() {

    }

}