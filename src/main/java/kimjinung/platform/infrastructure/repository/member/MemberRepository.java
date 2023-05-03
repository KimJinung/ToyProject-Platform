package kimjinung.platform.infrastructure.repository.member;

import kimjinung.platform.domain.member.Member;

public interface MemberRepository {
    public void save(Member member);
    public Member findById(Long id);

}
