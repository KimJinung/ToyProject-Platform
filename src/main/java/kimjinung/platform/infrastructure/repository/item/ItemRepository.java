package kimjinung.platform.infrastructure.repository.item;


import kimjinung.platform.domain.item.Item;

public interface ItemRepository {
    public void save(Item item);

    public Item findById(Long id);

    public Item findByName(String name);
}
