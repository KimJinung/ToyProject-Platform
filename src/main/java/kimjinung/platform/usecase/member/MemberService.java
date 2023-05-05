package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.dto.member.MemberDTO;

public interface MemberService {
    void join(MemberDTO memberDTO);
}
