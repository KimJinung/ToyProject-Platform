package kimjinung.platform.usecase.category;


import kimjinung.platform.domain.item.Category;
import kimjinung.platform.dto.item.CategoryDTO;
import kimjinung.platform.infrastructure.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void register(CategoryDTO category) {
        String name = category.getName();
        List<String> childCategories = category.getChild();

        Category parentCategory = new Category(name, null);

        for (String childCategory : childCategories) {
            Category child = new Category(childCategory, parentCategory);
            parentCategory.addChild(child);
            categoryRepository.save(child);

        }

        categoryRepository.save(parentCategory);
    }

    @Override
    public Category find(String name) {
        return categoryRepository.findByName(name);
    }
}
