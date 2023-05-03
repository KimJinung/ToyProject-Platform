package kimjinung.platform.infrastructure.repository.item;

import kimjinung.platform.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



@Rollback(value = false)
@Transactional(readOnly = true)
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void testSaveAndFindByName() {
        Item item = new Item();

        item.setName("Pizza");

        itemRepository.save(item);

        Item foundItem = itemRepository.findByName("Pizza");

        Assertions.assertThat(foundItem.getName()).isEqualTo(item.getName());
    }

    @Test
    @Transactional
    public void testFindById() {
        Item item = new Item();

        item.setName("Pizza");

        itemRepository.save(item);

        Item foundItem = itemRepository.findById(1L);

        Assertions.assertThat(foundItem.getId()).isEqualTo(1L);
        Assertions.assertThat(foundItem.getName()).isEqualTo("Pizza");
    }
}