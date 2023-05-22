package kimjinung.platform.domain.shipment;

import kimjinung.platform.domain.member.Address;
import kimjinung.platform.domain.order.Order;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "shipment_id")
    private UUID id;

    @OneToOne(fetch = LAZY, cascade = PERSIST)
    private Order order;
    private Address address;
    private ShipmentStatus status;

    public Shipment() {
    }

    public Shipment(Order order, Address address) {
        this.order = order;
        this.address = address;
        this.status = ShipmentStatus.PENDING;
    }
}
