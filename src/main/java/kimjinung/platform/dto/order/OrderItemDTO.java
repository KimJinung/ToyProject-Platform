package kimjinung.platform.dto.order;

import lombok.Getter;

@Getter
public class OrderItemDTO {

    private Long id;
    private Integer count;

    public OrderItemDTO(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
