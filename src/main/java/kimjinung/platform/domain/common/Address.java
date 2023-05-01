package kimjinung.platform.domain.common;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    String city;
    String street;
    String zipCode;

    protected Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
