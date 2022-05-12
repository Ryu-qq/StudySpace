package mission.demo.domain.repository;

import mission.demo.api.dto.OrderItemResponseDto;
import mission.demo.api.dto.OrderResponseDto;
import mission.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select new mission.demo.api.dto.OrderResponseDto(o.id, m.name, o.createdDate, o.status, d.address ) from Order o left join o.member m left join o.delivery d where o.member.id =:memberId and o.id =:orderId")
    OrderResponseDto findOrderByMemberId(@Param("memberId") Long memberId, @Param("orderId") Long orderId);


    @Query("select new mission.demo.api.dto.OrderResponseDto(o.id, m.name, o.createdDate, o.status, d.address ) from Order o left join o.member m left join o.delivery d where o.member.id =:memberId")
    List<OrderResponseDto> findOrdersByMemberId(@Param("memberId") Long memberId);


    @Query("select new mission.demo.api.dto.OrderItemResponseDto(oi.order.id, oi.id, i.name, i.price, oi.count) from OrderItem oi left join oi.item i on oi.item.id =i.id where oi.order.id in :ordersId")
    List<OrderItemResponseDto> findOrderItemByOrderId(@Param("ordersId") List<Long> ordersId);


    Optional<Order> findByIdAndMemberId(Long orderId, Long memberId);
}
