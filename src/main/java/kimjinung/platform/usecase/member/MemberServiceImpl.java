package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Override
    public boolean enroll(Member member) {
        return false;
    }

    @Override
    public boolean withdrawal(Member member) {
        return false;
    }
}
