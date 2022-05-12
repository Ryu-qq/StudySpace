package mission.demo.service;


import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.OrderItemRequestDto;
import mission.demo.api.dto.OrderItemResponseDto;
import mission.demo.api.dto.OrderRequestDto;
import mission.demo.api.dto.OrderResponseDto;
import mission.demo.domain.*;
import mission.demo.domain.item.Item;
import mission.demo.domain.repository.ItemRepository;
import mission.demo.domain.repository.MemberRepository;
import mission.demo.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    /**
     * 주문 내역 단건 조회
     * @param orderId 단건 주문 번호
     * @return 주문 내역 단건
     */
    public OrderResponseDto findOrder (Long orderId, Long memberId){

        OrderResponseDto result = orderRepository.findOrderByMemberId(memberId, orderId);

        List<Long> orderIds = new ArrayList<>();
        orderIds.add(result.getOrderId());

        List<OrderItemResponseDto> orderItems = orderRepository.findOrderItemByOrderId(orderIds);

        Map<Long, List<OrderItemResponseDto>> orderMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemResponseDto::getOrderId));

        result.setOrderItem(orderMap.get(result.getOrderId()));

        return result;
    }


    /**
     * 주문 내역 전체 조회
     * @param memberId 주문 조회할 멤버 아이디
     * @return 주문 내역 전체
     */
    public List<OrderResponseDto> findOrderAll(Long memberId){

        List<OrderResponseDto> result = orderRepository.findOrdersByMemberId(memberId);

        List<Long> orderIds = result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());

        List<OrderItemResponseDto> orderItems = orderRepository.findOrderItemByOrderId(orderIds);


        Map<Long, List<OrderItemResponseDto>> orderMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemResponseDto::getOrderId));

        result.forEach(o->o.setOrderItem(orderMap.get(o.getOrderId())));

        return result;
    }


    /**
     * @param memberId 주문 생성한 회원 아이디
     * @param requestDto 아이템 아이디, 아이템 수량
     * @return 생성된 주문 아이디
     * @throws Exception 수량이 부족할때
     */

    @Transactional
    public Long order(Long memberId, OrderRequestDto requestDto) throws Exception {
        Member member = memberRepository.findById(memberId).get();

        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderItemRequestDto dto : requestDto.getItems()){

            Item item = itemRepository.findById(dto.getItemId()).get();
            //주문상품 생성
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), dto.getCount());

            orderItemList.add(orderItem);

        }

        //배송정보 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItemList);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }


    /**
     * 취소
     * @param orderId 취소할 주문 아이디
     * @param memberId 취소할 회원 아이디
     */

    @Transactional
    public Long cancelOrder(Long orderId, Long memberId){

        Order order = orderRepository.findByIdAndMemberId(orderId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id=" + orderId));

        order.cancel();
        return order.getId();
    }


}
