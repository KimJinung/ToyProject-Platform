package kimjinung.platform.usecase.member;

import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.member.Member;
import kimjinung.platform.dto.member.AddressDTO;
import kimjinung.platform.dto.member.MemberDTO;
import kimjinung.platform.infrastructure.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    @Override
    public boolean join(MemberDTO memberDTO) {
        String name = memberDTO.getName();

        if (repository.findByUsername(name).isPresent()) {
            return false;
        }

        String password = memberDTO.getPassword();
        AddressDTO addressDTO = memberDTO.getAddress();
        String city = addressDTO.getCity();
        String street = addressDTO.getStreet();
        String postCode = addressDTO.getPostCode();

        Address address = new Address(city, street, postCode);
        Member member = new Member(name, password, address);

        repository.save(member);
        return true;
    }

    @Override
    public Member searchById(Long id) {
        Optional<Member> member = repository.findById(id);

        return member.orElse(null);
    }

    @Override
    public Member search(String name) {
        Optional<Member> member = repository.findByUsername(name);

        return member.orElse(null);
    }


}
