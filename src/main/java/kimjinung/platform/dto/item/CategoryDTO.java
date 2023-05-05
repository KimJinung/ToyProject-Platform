package kimjinung.platform.dto.item;

import lombok.Getter;

import java.util.List;

@Getter
public class CategoryDTO {
    private String name;
    private List<String> child;

    public CategoryDTO(String name, List<String> child) {
        this.name = name;
        this.child = child;
    }
}