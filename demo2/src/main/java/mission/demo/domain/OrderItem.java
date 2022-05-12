package mission.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mission.demo.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="order_id")
    private Order order;

    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    /**
     *
     * @param item  주문한 아이템
     * @param orderPrice 주문 가격
     * @param count 주문 수량
량    * @return
     * @throws Exception
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) throws Exception {

        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();

        item.removeStock(count);

        return orderItem;
    }

    /**
     * 재고 수량 원복
     */
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * @return 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
