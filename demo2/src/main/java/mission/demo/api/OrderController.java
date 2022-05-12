package mission.demo.api;


import lombok.RequiredArgsConstructor;
import mission.demo.ApiResponse;
import mission.demo.api.auth.LoginMember;
import mission.demo.api.auth.SessionMember;
import mission.demo.api.dto.OrderRequestDto;
import mission.demo.api.dto.OrderResponseDto;
import mission.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 내역 전체 조회
     * @param member 주문한 회원
     * @return 주문한 전체 내역 조회
     */

    @GetMapping
    public ApiResponse<List<OrderResponseDto>> findOrderAll(@LoginMember SessionMember member){
        return ApiResponse.success("order-list", orderService.findOrderAll(member.getId()));
    }

    /**
     * 주문 내역 단건 조회
     * @param orderId 주문 아이디
     * @param member 주문한 회원
     * @return 주문 내역 단건
     */

    @GetMapping({"/{orderId}"})
    public ApiResponse<OrderResponseDto> findOrder(@PathVariable Long orderId, @LoginMember SessionMember member){
        return ApiResponse.success("order", orderService.findOrder(orderId, member.getId()));
    }

    /**
     * 주문을 생성한다.
     * @param requestDto 주문을 생성하기 위한 유저 아이디, 아이템 아이디, 아이템 수량
     * @return 주문 단건 조회로 리다이렉트 한다.
     * @throws Exception
     */

    @PostMapping()
    public Long createOrder(@ModelAttribute OrderRequestDto requestDto, @LoginMember SessionMember member, RedirectAttributes redirectAttributes) throws Exception {
        Long createOrderId = orderService.order(member.getId(), requestDto.getItemId(), requestDto.getCount());
        redirectAttributes.addAllAttributes("orderId", createOrderId);
        return "redirect:/post"
    }

    /**
     * 리뷰를 삭제한다.
     * @param orderId 취소할 주문 아이디
     * @param member 로그인한 사용자의 정보
     * @return 삭제한 주문의 Id
     */

    @DeleteMapping("/{postId}")
    public ApiResponse<Long> deletePost(@PathVariable Long orderId , @LoginMember SessionMember member){
        return ApiResponse.success("order-delete",  orderService.cancelOrder(orderId, member.getId()));
    }


}
