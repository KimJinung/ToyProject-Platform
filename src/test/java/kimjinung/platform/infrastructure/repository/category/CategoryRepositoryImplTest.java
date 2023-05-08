package kimjinung.platform.infrastructure.repository.category;

import kimjinung.platform.domain.item.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional(readOnly = true)
@SpringBootTest
class CategoryRepositoryImplTest {

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    void beforeEach() {
        Category parent = new Category("Digital", null);
        Category child = new Category("PC", parent);

        parent.addChild(child);

        repository.save(child);
        repository.save(parent);
    }

    @Test
    @Transactional
    void testFindById() {
        Category category = repository.findByName("PC");
        Long id = category.getId();
        Category result = repository.findById(id);

        assertThat(result).isEqualTo(category);

    }
    @Test
    @Transactional
    void testFindByName() {
        Category category = repository.findByName("PC");

        assertThat(category.getName()).isEqualTo("PC");

        assertThat(category.getParent().getName()).isEqualTo("Digital");

        for (Category childCategory : category.getParent().getChild()) {
            assertThat(childCategory.getParent().getName()).isEqualTo("Digital");
        }
    }

}