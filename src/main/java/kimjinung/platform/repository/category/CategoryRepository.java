package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;

import java.util.List;

public interface CategoryRepository {

    boolean save(Category category);
    Category findById(Long id);
    List<Category> findById(Long... id);
    Category findByName(String name);
}
