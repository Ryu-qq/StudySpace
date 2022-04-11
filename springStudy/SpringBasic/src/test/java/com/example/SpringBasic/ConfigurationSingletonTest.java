package com.example.SpringBasic;

import com.example.SpringBasic.OnlyJava.AppConfig;
import com.example.SpringBasic.OnlyJava.AppSpringConfig;
import com.example.SpringBasic.OnlyJava.member.MemberRepository;
import com.example.SpringBasic.OnlyJava.member.MemberServiceImpl;
import com.example.SpringBasic.OnlyJava.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppSpringConfig.class);

        MemberServiceImpl memberService  = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        
        AppSpringConfig bean = ac.getBean(AppSpringConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
    
    @Test
    void noConfigurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService  = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);


        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
    }

    @Test
    void ConfigClassType(){
        ApplicationContext ac1 = new AnnotationConfigApplicationContext(AppSpringConfig.class);
        ApplicationContext ac2 = new AnnotationConfigApplicationContext(AppConfig.class);


        AppSpringConfig bean1 = ac1.getBean(AppSpringConfig.class);
        AppConfig bean2 = ac2.getBean(AppConfig.class);


        System.out.println("bean.getClass() = " + bean1.getClass());
        System.out.println("bean.getClass() = " + bean2.getClass());

    }
}
