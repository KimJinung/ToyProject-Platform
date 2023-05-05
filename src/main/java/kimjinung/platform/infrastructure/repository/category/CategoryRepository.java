package kimjinung.platform.infrastructure.repository.category;

import kimjinung.platform.domain.item.Category;

public interface CategoryRepository {
    void save(Category category);

    Category findById(Long id);
    Category findByName(String name);
}
