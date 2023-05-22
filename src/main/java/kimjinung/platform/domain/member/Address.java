package kimjinung.platform.domain.member;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String string;
    private String zipCode;

    public Address() {

    }

    public Address(String city, String string, String zipCode) {
        this.city = city;
        this.string = string;
        this.zipCode = zipCode;
    }
}
