package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private final EntityManager em;

    @Override
    public boolean save(Category category) {
        return false;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Category>> findById(List<Long> id) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.empty();
    }
}
