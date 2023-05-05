package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.item.ItemDTO;

import java.util.List;

public interface ItemService {

    void register(ItemDTO itemDTO);

    List<Item> find(String name);

    void remove(Long id);

    boolean addStock(Long id, int quantity);

    boolean reduceStock(Long id, int quantity);

}
