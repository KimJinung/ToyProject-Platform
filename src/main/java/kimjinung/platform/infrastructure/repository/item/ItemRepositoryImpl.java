package kimjinung.platform.infrastructure.repository.item;


import kimjinung.platform.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository{

    private final EntityManager em;

    @Override
    public void save(Item item) {
        em.persist(item);
    }

    @Override
    public boolean delete(Item item) {
        Long id = item.getId();

        em.remove(item);
        em.flush();
        em.clear();

        return findById(id) == null;
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

}
