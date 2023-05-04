package kimjinung.platform.usecase.order;

import kimjinung.platform.dto.ItemDTO;
import kimjinung.platform.dto.OrderInfoDTO;

public interface OrderService {

    void order(OrderInfoDTO orderInfo);

}
