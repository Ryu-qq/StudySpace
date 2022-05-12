package mission.demo.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    private Long itemId;
    private int count;

    public OrderRequestDto(Long memberId, Long itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }
}
