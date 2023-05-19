package kimjinung.platform.repository.member;

import kimjinung.platform.domain.member.Member;

import java.util.List;
import java.util.UUID;

public interface MemberRepository {

    boolean save(Member member);
    Member findById(UUID id);
    List<Member> findByName(String name);
}
