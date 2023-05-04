package kimjinung.platform.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderInfoDTO {
    private Long memberId;
    private String city;
    private String street;
    private String zipCode;
    private List<ItemDTO> items;
}
