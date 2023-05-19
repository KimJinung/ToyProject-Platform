package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private final EntityManager em;

    @Override
    public boolean save(Category category) {
        String name = category.getName();
        Category isAlreadyExist = findByName(name).orElse(null);

        if (isAlreadyExist != null) {
            return false;
        } else {
            System.out.println("isAlreadyExist = " + isAlreadyExist);
            em.persist(category);
            return true;
        }

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
        TypedQuery<Category> query = em.
                createQuery("select c from Category c where c.name = :name", Category.class)
                .setParameter("name", name);

        try {
            Category category = query.getSingleResult();
            return Optional.of(category);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
