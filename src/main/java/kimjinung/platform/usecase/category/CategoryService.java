package kimjinung.platform.usecase.category;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.dto.item.CategoryDTO;

public interface CategoryService {

    void register(CategoryDTO categoryDTO);

    Category find(String name);
}
