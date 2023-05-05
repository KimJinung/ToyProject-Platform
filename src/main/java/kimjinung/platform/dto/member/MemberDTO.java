package kimjinung.platform.dto.member;

import lombok.Getter;

@Getter
public class MemberDTO {
    private String name;
    private String password;
    private AddressDTO address;

    public MemberDTO(String name, String password, AddressDTO address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }


}
