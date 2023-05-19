package kimjinung.platform.usecase.item;

import kimjinung.platform.dto.ItemDTO;

import java.util.UUID;

public interface ItemService {

    UUID register(ItemDTO itemDTO);
}
