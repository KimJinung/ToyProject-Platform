package kimjinung.platform.repository.item;

import kimjinung.platform.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private final EntityManager em;

    @Override
    public UUID save(Item item) {
        em.persist(item);
        return item.getId();
    }

    @Override
    public Optional<Item> findById(UUID id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<List<Item>> findByName(String name) {
        List<Item> items = em.createQuery("select i from Item i where name = :name", Item.class)
                .setParameter("name", name)
                .getResultList();

        return Optional.ofNullable(items);
    }
}
