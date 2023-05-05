package kimjinung.platform.infrastructure.repository.category;

import kimjinung.platform.domain.item.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

    private final EntityManager em;

    @Override
    public void save(Category category) {
        em.persist(category);
    }

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public Category findByName(String name) {
         return (Category) em.createQuery("select c from Category c where name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
