package kimjinung.platform.infrastructure.repository.item;


import kimjinung.platform.domain.item.Item;

import java.util.List;

public interface ItemRepository {
    void save(Item item);

    boolean delete(Long id);

    Item findById(Long id);

    List<Item> findByName(String name);

}
