package kimjinung.platform.usecase.member;


import kimjinung.platform.domain.member.Member;
import kimjinung.platform.dto.member.AddressDTO;
import kimjinung.platform.dto.member.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService service;

    @Test
    @Transactional
    void testJoinAndSearch() {
        AddressDTO addressDTO = new AddressDTO("KY", "SW", "95");
        MemberDTO memberDTO = new MemberDTO("JinungKim", "0410", addressDTO);
        boolean joinResult = service.join(memberDTO);

        assertThat(joinResult).isTrue();

        Member member = service.search(memberDTO.getName());

        assertThat(member.getName()).isEqualTo("JinungKim");
    }

    @Test
    @Transactional
    void testJoin_ThrowAlreadyExistMember() {
        AddressDTO addressDTO = new AddressDTO("KY", "SW", "95");
        MemberDTO memberDTO = new MemberDTO("JinungKim", "0410", addressDTO);
        boolean joinResult = service.join(memberDTO);

        assertThat(joinResult).isTrue();

        boolean failJoin = service.join(memberDTO);
        assertThat(failJoin).isFalse();
    }
}