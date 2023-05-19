package kimjinung.platform.repository.category;

import kimjinung.platform.domain.item.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class CategoryRepositoryImplTest {

    Category category;

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    void beforeEach() {
        category = new Category(null, "Digital");
        repository.save(category);
    }

    @Test
    void testFindById() {
        Long id = category.getId();

        Category result = repository.findById(id).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Digital");

    }

    @Test
    void testFindByListId() {
        Category macBook = new Category(category.getId(), "MacBook");
        repository.save(macBook);

        List<Long> ids = Arrays.asList(category.getId(), macBook.getId());
        List<Category> categories = repository.findById(ids).orElse(null);

        assertThat(categories).isNotEmpty();
        assertThat(categories.size()).isEqualTo(2);
        assertThat(categories).contains(category, macBook);
    }

    @Test
    void testFindByName() {
        Category result = repository.findByName("Digital").orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Digital");
        assertThat(result.getParentId()).isNull();

    }
}