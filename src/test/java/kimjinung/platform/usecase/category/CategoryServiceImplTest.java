package kimjinung.platform.usecase.category;

import kimjinung.platform.domain.item.Category;
import kimjinung.platform.dto.item.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


@Transactional(readOnly = true)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    @Test
    @Transactional
    @Rollback(value = false)
    void findTest() {
        ArrayList<String> child = new ArrayList<>();
        child.add("PC");

        CategoryDTO category = new CategoryDTO("Digital", child);

        service.add(category);

        Category foundCategory = service.find("Digital");

        assertThat(foundCategory.getName()).isEqualTo("Digital");
        assertThat(foundCategory.getParent()).isNull();
    }
}