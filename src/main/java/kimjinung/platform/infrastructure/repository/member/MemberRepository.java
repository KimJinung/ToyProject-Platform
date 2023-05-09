package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String name);

}
