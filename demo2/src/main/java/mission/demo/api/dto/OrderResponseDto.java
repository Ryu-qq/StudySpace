package mission.demo.api.dto;


import lombok.Data;
import mission.demo.domain.Address;
import mission.demo.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemResponseDto> orderItem;

    public OrderResponseDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
