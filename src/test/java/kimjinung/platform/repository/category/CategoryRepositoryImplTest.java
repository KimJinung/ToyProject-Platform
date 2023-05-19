package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional(readOnly = true)
class CategoryRepositoryImplTest {

    Category category;

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    void beforeEach() {
        category = new Category(null, "Digital");
    }

    @Transactional
    @Test
    void testSave() {
        boolean result = repository.save(category);

        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testFindById() {
    }

    @Test
    void testFindByListId() {
    }

    @Test
    void testFindByName() {

    }
}