package kimjinung.platform.usecase.category;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.dto.item.CategoryDTO;

public interface CategoryService {

    void add(CategoryDTO categoryDTO);

    Category find(String name);
}