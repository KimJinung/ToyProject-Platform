package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    boolean save(Category category);
    Optional<Category> findById(Long id);
    Optional<List<Category>> findById(List<Long> id);
    Optional<Category> findByName(String name);
}
