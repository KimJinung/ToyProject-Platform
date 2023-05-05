package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.dto.member.AddressDTO;
import kimjinung.platform.dto.member.MemberDTO;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberDTO memberDTO) {
        String name = memberDTO.getName();
        String password = memberDTO.getPassword();
        AddressDTO addressDTO = memberDTO.getAddress();
        String city = addressDTO.getCity();
        String street = addressDTO.getStreet();
        String postCode = addressDTO.getPostCode();

        Address address = new Address(city, street, postCode);
        Member member = new Member(name, password, address);

        memberRepository.save(member);
    }

}
