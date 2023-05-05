package kimjinung.platform.dto.order;

import kimjinung.platform.dto.member.AddressDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderInfoDTO {
    private Long memberId;
    private AddressDTO addressDTO;
    private List<OrderItemDTO> items;

    public OrderInfoDTO(Long memberId, AddressDTO addressDTO, List<OrderItemDTO> items) {
        this.memberId = memberId;
        this.addressDTO = addressDTO;
        this.items = items;
    }
}
