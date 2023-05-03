package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;

public interface MemberRepository {
    public Long save(Member member);
    public Member findById(Long id);

}
