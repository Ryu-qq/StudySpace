package com.example.SpringBasic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);


        //ThreadA: A사용자 10000원 주문
        int userAPrice = stateFulService1.order("userA", 10000);

        //ThreadB: B사용자 20000원 주문
        int userBPrice = stateFulService2.order("userB", 20000);


        System.out.println("userAPrice = " + userAPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);

    }


    static class TestConfig{

        @Bean
        public StateFulService stateFulService(){

            return new StateFulService();
        }
    }
}
