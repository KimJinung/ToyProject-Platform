package kimjinung.platform.domain.shipment;

import kimjinung.platform.domain.order.Order;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;

@Getter
@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "shipment_id")
    private UUID id;

    @OneToOne(cascade = PERSIST)
    private Order order;
    private ShipmentStatus status;

    public Shipment() {
    }

    public Shipment(Order order) {
        this.order = order;
        this.status = ShipmentStatus.PENDING;
    }
}
