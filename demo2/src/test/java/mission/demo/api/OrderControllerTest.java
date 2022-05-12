package mission.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import mission.demo.api.dto.OrderItemRequestDto;
import mission.demo.api.dto.OrderRequestDto;
import mission.demo.api.dto.OrderResponseDto;
import mission.demo.domain.Order;
import mission.demo.domain.item.Item;
import mission.demo.domain.repository.ItemRepository;
import mission.demo.domain.repository.MemberRepository;
import mission.demo.domain.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void Order_주문한다() throws Exception {

        //given
        List<OrderItemRequestDto> itemList = new ArrayList<>();

        //자바 책, 개당 10000원
        OrderItemRequestDto item1 = OrderItemRequestDto.builder()
                .itemId(6L)
                .count(2)
                .build();
        //나이키, 개당 10000원
        OrderItemRequestDto item2 = OrderItemRequestDto.builder()
                .itemId(14L)
                .count(2)
                .build();

        itemList.add(item1);
        itemList.add(item2);

        OrderRequestDto requestDto = new OrderRequestDto(itemList);

        String url = "http://localhost:" + port + "/api/orders";

        //when
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Member fbtkdals2")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk());


        //then
        List<OrderResponseDto> ordersByMemberId = orderRepository.findOrdersByMemberId(1L);
        OrderResponseDto findOrder = ordersByMemberId.get(ordersByMemberId.size() - 1);

        //자바 수량 9 -> 7
        //나이키 수량 6 -> 4
        int javaQuantity = itemRepository.findById(6L).get().getStockQuantity();
        int nikeQuantity = itemRepository.findById(14L).get().getStockQuantity();

        Assertions.assertThat(javaQuantity).isEqualTo(7);
        Assertions.assertThat(nikeQuantity).isEqualTo(4);
        Assertions.assertThat(findOrder.getOrderItem().size()).isEqualTo(2);


    }

    @Test
    public void Order_재고수량초과주문() throws Exception {

        //given
        List<OrderItemRequestDto> itemList = new ArrayList<>();

        //자바 책
        OrderItemRequestDto item1 = OrderItemRequestDto.builder()
                .itemId(6L)
                .count(100)
                .build();


        itemList.add(item1);

        OrderRequestDto requestDto = new OrderRequestDto(itemList);

        String url = "http://localhost:" + port + "/api/orders";

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Member fbtkdals2")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void Order_주문취소() throws Exception {

        //Member 1L이 주문한 오더로 자바 1권 스프링 2권
        Order findOrder = orderRepository.findById(3L).get();

        String url = "http://localhost:" + port + "/api/orders/" + findOrder.getId();

        mvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Member fbtkdals2")
                .content(new ObjectMapper().writeValueAsString("")))
                .andDo(print())
                .andExpect(status().isOk());


        //then
        Item java = itemRepository.findById(6L).get();
        Item spring = itemRepository.findById(8L).get();

        //주문취소로 재고수량 원복
        Assertions.assertThat(java.getStockQuantity()).isEqualTo(10);
        Assertions.assertThat(spring.getStockQuantity()).isEqualTo(10);

    }

}