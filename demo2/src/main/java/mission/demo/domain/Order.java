package mission.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Time {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 편의 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    //생성 매서드

    /**
     * 주문 생성
     * @param member 주문한 회원
     * @param delivery 주문한 배송
     * @param orderItems 주문한 아이템들들     * @return
     */
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems ){

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        return order;
    }


    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() ==DeliveryStatus.COMPLETE){
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능 합니다");
        }
        this.status =OrderStatus.CANCEL;
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    /**
     * * @return 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice).sum();
    }


}
