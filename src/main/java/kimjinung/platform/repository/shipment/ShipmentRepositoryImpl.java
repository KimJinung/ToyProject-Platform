package kimjinung.platform.repository.shipment;

import kimjinung.platform.domain.order.Order;
import kimjinung.platform.domain.shipment.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class ShipmentRepositoryImpl implements ShipmentRepository {

    private final EntityManager em;

    @Override
    public Optional<Shipment> findById(UUID id) {
        Shipment shipment = em.find(Shipment.class, id);
        return Optional.ofNullable(shipment);
    }

    @Override
    public Optional<Shipment> findByOrder(Order order) {
        List<Shipment> shipment = em.createQuery("select s from Shipment s where s.order.id = :orderId", Shipment.class)
                .setParameter("orderId", order.getId())
                .getResultList();

        if (!shipment.isEmpty()) {
            return Optional.ofNullable(shipment.get(0));
        } else {
            return Optional.empty();
        }
    }
}
