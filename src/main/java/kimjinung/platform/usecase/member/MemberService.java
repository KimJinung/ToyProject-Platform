package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.member.Member;

public interface MemberService {
    boolean enroll(Member member);

    boolean withdrawal(Member member);
}
