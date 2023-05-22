package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private final EntityManager em;

    @Override
    public void save(Category category) {
        em.persist(category);
        em.flush();
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category = em.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<List<Category>> findById(List<Long> id) {
        List<Category> categories = em
                .createQuery("select c from Category c where c.id in :categoryIds", Category.class)
                .setParameter("categoryIds", id)
                .getResultList();

        return Optional.ofNullable(categories);
    }

    @Override
    public Optional<Category> findByName(String name) {
        List<Category> result = em.
                createQuery("select c from Category c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getResultList();

        if (!result.isEmpty()) {
            return Optional.of(result.get(0));
        } else {
            return Optional.empty();
        }
    }
}
