package kimjinung.platform.repository.shipment;

import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.shipment.Shipment;

import java.util.UUID;

public interface shipmentRepository {
    Shipment findById(UUID uuid);
    Shipment findByOrder(Order order);
}
