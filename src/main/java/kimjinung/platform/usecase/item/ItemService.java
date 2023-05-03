package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Item;

import java.util.List;

public interface ItemService {

    void register(Item item);

    void remove(Long id);

    boolean addStock(Long id, int quantity);

    boolean reduceStock(Long id, int quantity);
}
