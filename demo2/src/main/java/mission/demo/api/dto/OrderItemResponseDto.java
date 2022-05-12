package mission.demo.api.dto;


import lombok.Data;

@Data
public class OrderItemResponseDto {

    private Long orderId;
    private Long orderItemId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemResponseDto(Long orderId, Long orderItemId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

}
