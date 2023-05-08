package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class MemberRepositoryImplTest {

    Address address;
    Member member;

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository repository;

    @BeforeEach
    void beforeEach() {
        address = new Address("Ky", "Sw", "95");
        member = new Member("JinungKim", "0410", address);

        repository.save(member);

        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void testFindByName() {
        List<Member> members = repository.findByName("JinungKim");
        Member result = members.stream().findFirst().orElse(null);

        assertThat(members).isNotEmpty();
        assertThat(result.getName()).isEqualTo("JinungKim");
    }

    @Test
    @Transactional
    void testFindById() {
        Long id = member.getId();
        Member result = repository.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("JinungKim");
        assertThat(result.getAddress()).isEqualTo(address);
    }

}