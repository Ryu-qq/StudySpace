package mission.demo.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    private List<OrderItemRequestDto> items;

    public OrderRequestDto(List<OrderItemRequestDto> items) {
        this.items = items;
    }
}
