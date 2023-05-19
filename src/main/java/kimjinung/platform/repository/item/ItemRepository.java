package kimjinung.platform.repository.item;

import kimjinung.platform.domain.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository {

    boolean save(Item item);
    Item findById(UUID id);
    List<Item> findByName(String name);

}
