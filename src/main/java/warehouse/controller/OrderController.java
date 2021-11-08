package warehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouse.model.dto.request.OrderRequestDto;
import warehouse.model.dto.response.OrderResponseDto;
import warehouse.service.OrderService;
import warehouse.service.mapper.impl.OrderMapper;

@RestController
@RequestMapping
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping("/new-order")
    OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderMapper.mapToDto(orderService.placeOrder(orderRequestDto));
    }
}
