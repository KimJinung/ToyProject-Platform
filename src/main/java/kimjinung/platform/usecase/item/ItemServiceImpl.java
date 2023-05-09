package kimjinung.platform.usecase.item;


import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.CategoryItem;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.item.ItemDTO;
import kimjinung.platform.exception.NotEnoughStockException;
import kimjinung.platform.infrastructure.repository.category.CategoryRepository;
import kimjinung.platform.infrastructure.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void register(ItemDTO itemDTO) {
        String name = itemDTO.getName();
        Integer price = itemDTO.getPrice();
        Integer stockQuantity = itemDTO.getStockQuantity();
        Item item = new Item(name, price, stockQuantity);

        List<Long> categoryIds = itemDTO.getCategories();

        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId);
            CategoryItem categoryItem = new CategoryItem(item, category);
            item.addCategoryItem(categoryItem);
        }

        itemRepository.save(item);
    }

    @Override
    public List<Item> find(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public boolean remove(Long id) {
        Item item = itemRepository.findById(id);

        if (item == null) {
            return false;
        }

        return itemRepository.delete(item);
    }

    @Override
    public boolean addStock(Long id, int quantity) {
        Item targetItem = itemRepository.findById(id);

        targetItem.addStock(quantity);

        return true;
    }

    @Override
    public boolean reduceStock(Long id, int quantity) throws NotEnoughStockException {
        Item targetItem = itemRepository.findById(id);

        try {
            targetItem.reduceStock(quantity);
        } catch (NotEnoughStockException e) {
            return false;
        }

        return true;
    }
}
