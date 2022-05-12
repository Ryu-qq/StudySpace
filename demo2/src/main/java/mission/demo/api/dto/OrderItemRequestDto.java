package mission.demo.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequestDto {

    private Long itemId;
    private int count;

    @Builder
    public OrderItemRequestDto(Long itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }
}
