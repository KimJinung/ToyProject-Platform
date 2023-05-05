package kimjinung.platform.dto.member;

import lombok.Getter;

@Getter
public class AddressDTO {
    private String city;
    private String street;
    private String postCode;

    public AddressDTO(String city, String street, String postCode) {
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }
}
