package kimjinung.platform.usecase.member;

import kimjinung.platform.dto.MemberDTO;

import java.util.UUID;

public interface MemberService {
    UUID join(MemberDTO memberDTO);
}
