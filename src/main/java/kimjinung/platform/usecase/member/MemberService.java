package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.dto.member.MemberDTO;

import java.util.List;

public interface MemberService {
    boolean join(MemberDTO memberDTO);
    Member searchById(Long id);
    Member search(String name);
}
