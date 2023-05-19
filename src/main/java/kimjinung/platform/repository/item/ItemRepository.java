package kimjinung.platform.repository.item;

import kimjinung.platform.domain.item.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository {

    UUID save(Item item);
    Optional<Item> findById(UUID id);
    Optional<List<Item>> findByName(String name);

}
