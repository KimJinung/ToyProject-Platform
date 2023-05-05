package kimjinung.platform.dto.item;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemDTO {
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private List<Long> categories;

    public ItemDTO(String name, Integer price, Integer stockQuantity, List<Long> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }
}
