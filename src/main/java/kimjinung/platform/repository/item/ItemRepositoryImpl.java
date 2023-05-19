package kimjinung.platform.repository.item;

import kimjinung.platform.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private final EntityManager em;

    @Override
    public UUID save(Item item) {
        return UUID.randomUUID();
    }

    @Override
    public Optional<Item> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Item>> findByName(String name) {
        return Optional.empty();
    }
}
