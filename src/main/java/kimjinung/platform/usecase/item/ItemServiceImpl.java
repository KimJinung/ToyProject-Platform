package kimjinung.platform.usecase.item;


import kimjinung.platform.domain.item.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public boolean register(Item item) {
        return false;
    }

    @Override
    public Long getIdByName(String name) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public List<Item> searchByKeyword(String keyword) {
        return null;
    }
}
