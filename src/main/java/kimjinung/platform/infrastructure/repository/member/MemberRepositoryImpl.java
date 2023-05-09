package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            Member member = em.find(Member.class, id);
            return Optional.of(member);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByUsername(String name) throws NoResultException {
        try {
            Member member = (Member) em.createQuery("select m from Member m where name = :name")
                    .setParameter("name", name)
                    .setMaxResults(1) // Refactor test code
                    .getSingleResult();
            return Optional.of(member);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
