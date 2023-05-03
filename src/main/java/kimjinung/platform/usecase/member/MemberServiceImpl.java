package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.member.Member;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void enroll(Member member) {
        memberRepository.save(member);
    }

}
