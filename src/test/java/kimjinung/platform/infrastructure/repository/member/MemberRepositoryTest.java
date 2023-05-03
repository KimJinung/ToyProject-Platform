package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testSave() {
        Member member = new Member();

        member.setName("JinungKim");

        Member foundMember = memberRepository.findById(1L);

        Assertions.assertThat(foundMember.getName()).isEqualTo("JinungKim");

    }

}