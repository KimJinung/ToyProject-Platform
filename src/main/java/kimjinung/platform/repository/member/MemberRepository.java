package kimjinung.platform.repository.member;

import kimjinung.platform.domain.member.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    boolean save(Member member);
    Optional<Member> findById(UUID id);
    Optional<List<Member>> findByName(String name);
}
