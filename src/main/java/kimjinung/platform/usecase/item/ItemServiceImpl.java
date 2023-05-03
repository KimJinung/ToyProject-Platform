package kimjinung.platform.usecase.item;


import kimjinung.platform.domain.item.Item;
import kimjinung.platform.exception.NotEnoughStockEx;
import kimjinung.platform.infrastructure.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void register(Item item) {
        itemRepository.save(item);

    }


    @Override
    public void remove(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public boolean addStock(Long id, int quantity) {
        Item targetItem = itemRepository.findById(id);

        targetItem.addStock(quantity);

        return true;
    }

    @Override
    public boolean reduceStock(Long id, int quantity) throws NotEnoughStockEx {
        Item targetItem = itemRepository.findById(id);

        try {
            targetItem.reduceStock(quantity);
        } catch (NotEnoughStockEx e) {
            return false;
        }

        return true;
    }
}
