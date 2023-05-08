package kimjinung.platform.domain.common;


import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) return false;

        Address addressObj = (Address) obj;

        return Objects.equals(this.city, addressObj.city) &&
                Objects.equals(this.street, addressObj.street) &&
                Objects.equals(this.zipCode, addressObj.zipCode);
    }
}
