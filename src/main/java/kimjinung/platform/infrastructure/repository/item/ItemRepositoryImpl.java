package kimjinung.platform.infrastructure.repository.item;


import kimjinung.platform.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository{

    private final EntityManager em;

    @Override
    public void save(Item item) {
        em.persist(item);
    }

    @Override
    public boolean delete(Long id) {
        em.remove(id);
        em.flush();
        em.clear();

        Item result = findById(id);

        return result == null;
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public Item findByName(String name) {
        return (Item) em.createQuery("select i from Item i where i.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

}
