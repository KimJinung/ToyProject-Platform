package kimjinung.platform.domain.shipment;

import kimjinung.platform.domain.base.BaseEntity;
import kimjinung.platform.domain.common.Address;
import kimjinung.platform.domain.order.Order;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Shipment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipment_id")
    private Long id;

    @OneToOne(fetch = LAZY, mappedBy = "shipment")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;
}
