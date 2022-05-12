package mission.demo.api.dto;


import lombok.Data;

@Data
public class OrderItemResponseDto {

    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemResponseDto(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
