package kimjinung.platform.repository.member;

import kimjinung.platform.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberRepositoryImplTest {

    Member member;

    @Autowired
    MemberRepository repository;

    @BeforeEach
    void beforeEach() {
        member = new Member("Jinung Kim", "0410");

        repository.save(member);
    }

    @Test
    void testFindById() {
        UUID id = member.getId();

        Member result = repository.findById(id).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Jinung Kim");
    }

    @Test
    void testFindByName() {
        repository.save(new Member("Jinung Kim", "1004"));

        List<Member> members = repository.findByName("Jinung Kim").orElse(null);

        assertThat(members).isNotNull();
        assertThat(members.size()).isEqualTo(2);
        members.forEach(m -> assertThat(m.getName()).isEqualTo("Jinung Kim"));

    }
}