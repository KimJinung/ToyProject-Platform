package kimjinung.platform.usecase.item;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.domain.item.Item;
import kimjinung.platform.dto.ItemDTO;
import kimjinung.platform.repository.category.CategoryRepository;
import kimjinung.platform.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public UUID register(ItemDTO itemDTO) {
        List<Long> categoryIds = itemDTO.getCategoryIds();
        List<Category> categories = categoryRepository.findById(categoryIds).orElse(null);

        String name = itemDTO.getName();
        int price = itemDTO.getPrice();
        int stockQuantity = itemDTO.getStockQuantity();

        Item item = new Item(name, price, stockQuantity, categories);

        return itemRepository.save(item);

    }

}
