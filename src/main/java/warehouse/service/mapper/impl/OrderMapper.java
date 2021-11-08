package warehouse.service.mapper.impl;

import java.util.Map;
import org.springframework.stereotype.Component;
import warehouse.model.dto.response.OrderResponseDto;
import warehouse.service.mapper.ResponseDtoMapper;

@Component
public class OrderMapper implements ResponseDtoMapper<Map<String, Double>, OrderResponseDto> {

    @Override
    public OrderResponseDto mapToDto(Map<String, Double> model) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setSkuAndPrice(model);
        return responseDto;
    }
}
