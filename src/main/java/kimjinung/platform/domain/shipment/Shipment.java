package kimjinung.platform.domain.shipment;

import kimjinung.platform.domain.member.Address;
import kimjinung.platform.domain.order.Order;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "shipment_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(mappedBy = "shipment")
    private Order order;
    private Address address;

    @Enumerated(STRING)
    private ShipmentStatus status;

    public Shipment() {
    }

    public Shipment(Order order, Address address) {
        this.order = order;
        this.address = address;
        this.status = ShipmentStatus.PENDING;
    }

    public void shipStart() {
        this.status = ShipmentStatus.SHIPPED;
    }

    public void shipComplete() {
        this.status = ShipmentStatus.COMPLETE;
    }
}
