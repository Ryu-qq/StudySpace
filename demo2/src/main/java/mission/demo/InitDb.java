package mission.demo;

import lombok.RequiredArgsConstructor;
import mission.demo.domain.*;
import mission.demo.domain.item.Book;
import mission.demo.domain.item.Clothes;
import mission.demo.domain.item.Lp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() throws Exception {
        initService.dbInit();
    }



    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() throws Exception {

            Member member1 = createMember("ryu","fbtkdals2" , AccountType.MEMBER,"서울", "1","11111");
            Member member2 = createMember("sang","fbtkdals3" , AccountType.MEMBER, "서울", "2","22222");

            em.persist(member1);
            em.persist(member2);

            Book book1 = createBook("자바", 10000, 10);
            Book book2 = createBook("자바스크립트", 15000, 10);
            Book book3 = createBook("스프링", 35000, 10);

            Clothes clothes = createClothes("나이키",10000, 10);
            Lp lp = createLp("위켄드", 20000, 2);


            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book3, 20000, 2);

            OrderItem orderItem3 = OrderItem.createOrderItem(lp, 20000, 2);
            OrderItem orderItem4 = OrderItem.createOrderItem(clothes, 20000, 2);

            OrderItem orderItem5 = OrderItem.createOrderItem(clothes, 10000, 2);
            OrderItem orderItem6 = OrderItem.createOrderItem(book2, 15000, 2);

            List<OrderItem> list1 = new ArrayList<>();
            List<OrderItem> list2 = new ArrayList<>();
            List<OrderItem> list3 = new ArrayList<>();

            list1.add(orderItem1);
            list1.add(orderItem2);
            list2.add(orderItem3);
            list2.add(orderItem4);
            list3.add(orderItem5);
            list3.add(orderItem6);


            Order order1 = Order.createOrder(member1, createDelivery(member1), list1);

            Order order2 = Order.createOrder(member2, createDelivery(member1), list2);

            Order order3 = Order.createOrder(member1, createDelivery(member1), list3);

            em.persist(order1);
            em.persist(order2);
            em.persist(order3);


        }




        private Member createMember(String name, String account_id, AccountType account_type, String city, String street,
                                    String zipcode) {
            return Member.builder()
                    .name(name)
                    .account_id(account_id)
                    .account_type(account_type)
                    .address(new Address(city, street, zipcode))
                    .build();
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Lp createLp(String artist, int price, int stockQuantity){
            Lp lp = new Lp();
            lp.setName(artist);
            lp.setPrice(price);
            lp.setStockQuantity(stockQuantity);
            return lp;
        }

        private Clothes createClothes(String brand, int price, int stockQuantity){
            Clothes clothes = new Clothes();
            clothes.setName(brand);
            clothes.setPrice(price);
            clothes.setStockQuantity(stockQuantity);
            return clothes;
        }


        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);
            return delivery;
        }
    }
}
