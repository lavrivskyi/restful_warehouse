package warehouse.service;

import java.util.Map;
import warehouse.model.dto.request.OrderRequestDto;

public interface OrderService {
    Map<String, Double> placeOrder(OrderRequestDto orderRequestDto);
}
