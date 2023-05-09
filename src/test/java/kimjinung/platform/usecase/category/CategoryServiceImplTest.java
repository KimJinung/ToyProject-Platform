package kimjinung.platform.usecase.category;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.dto.item.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    @BeforeEach
    void beforeEach() {
        ArrayList<String> child = new ArrayList<>();
        child.add("PC");
        CategoryDTO category = new CategoryDTO("Digital", child);

        service.register(category);
    }

    @Test
    @Transactional
    void testFind() {
        Category foundCategory = service.find("Digital");

        assertThat(foundCategory.getName()).isEqualTo("Digital");
        assertThat(foundCategory.getParent()).isNull();
    }
}