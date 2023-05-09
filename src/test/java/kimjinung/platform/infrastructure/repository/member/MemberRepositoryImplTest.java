package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

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
        address = new Address("KY", "SW", "95");
        member = new Member("JinungKim", "0410", address);

        repository.save(member);

        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testFindByName() {
        Optional<Member> result = repository.findByUsername("JinungKim");

        assertThat(this.member.getName()).isEqualTo(result.get().getName());
    }

    @Test
    @Transactional
    void testFindById() {
        Long id = member.getId();
        Optional<Member> foundMember = repository.findById(id);
        Member result = foundMember.orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("JinungKim");
        assertThat(result.getAddress()).isEqualTo(address);
    }

}