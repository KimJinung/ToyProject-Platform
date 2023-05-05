package kimjinung.platform.infrastructure.repository.category;

import kimjinung.platform.domain.item.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional(readOnly = true)
@SpringBootTest
class CategoryRepositoryImplTest {


    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void beforeEach() {
        Category parent = new Category("Digital", null);
        Category child = new Category("PC", parent);

        parent.addChild(child);

        categoryRepository.save(child);
        categoryRepository.save(parent);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findByNameTest() {
        Category result = categoryRepository.findByName("PC");

        assertThat(result.getName()).isEqualTo("PC");

        assertThat(result.getParent().getName()).isEqualTo("Digital");

        for (Category childCategory : result.getParent().getChild()) {
            assertThat(childCategory.getParent().getName()).isEqualTo("Digital");
        }
    }

}