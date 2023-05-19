package kimjinung.platform.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemDTO {

    private String name;
    private int price;
    private int stockQuantity;
    private List<Long> categoryIds;

}
