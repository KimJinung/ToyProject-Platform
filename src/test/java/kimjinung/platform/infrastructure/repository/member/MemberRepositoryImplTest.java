package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testSave() {
        Address address = new Address("Ky", "Sw", "95");

        Member member = new Member("JinungKim", "0410", address);

        memberRepository.save(member);

        Member foundMember = memberRepository.findById(1L);

        Assertions.assertThat(foundMember.getName()).isEqualTo("JinungKim");
    }

}