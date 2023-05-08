package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long id);

    List<Member> findByName(String name);

}
