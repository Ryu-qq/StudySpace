package mission.demo.service;

import mission.demo.domain.Address;
import mission.demo.domain.Member;
import mission.demo.domain.Order;
import mission.demo.domain.OrderStatus;
import mission.demo.domain.item.Book;
import mission.demo.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문()throws Exception{

        //given
        Member member = Member.builder()
                .name("ryu")
                .address(new Address("서울","용산", "12345"))
                .build();

        em.persist(member);


        Book book = new Book();
        book.setName("가즈아");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus().name());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * orderCount,getOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }
}