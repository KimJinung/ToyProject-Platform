package kimjinung.platform.repository.member;

import kimjinung.platform.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(UUID id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<List<Member>> findByName(String name) {
        List<Member> members = em.createQuery("select m from Member m where name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return Optional.ofNullable(members);
    }
}
