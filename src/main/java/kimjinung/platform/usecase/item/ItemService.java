package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Item;

import java.util.List;

public interface ItemService {

    boolean register(Item item);

    boolean remove(Long id);

    Long getIdByName(String name);

    List<Item> searchByKeyword(String keyword);
}
