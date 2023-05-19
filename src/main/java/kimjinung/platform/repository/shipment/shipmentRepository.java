package kimjinung.platform.repository.shipment;

import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.shipment.Shipment;

import java.util.Optional;
import java.util.UUID;

public interface shipmentRepository {
    Optional<Shipment> findById(UUID uuid);
    Optional<Shipment> findByOrder(Order order);
}
