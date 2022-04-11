package com.example.SpringBasic.OnlyJava;

import com.example.SpringBasic.OnlyJava.discount.DiscountPolicy;
import com.example.SpringBasic.OnlyJava.discount.FixDiscountPolicy;
import com.example.SpringBasic.OnlyJava.member.MemberRepository;
import com.example.SpringBasic.OnlyJava.member.MemoryMemberRepositoryImpl;
import com.example.SpringBasic.OnlyJava.member.MemberService;
import com.example.SpringBasic.OnlyJava.member.MemberServiceImpl;
import com.example.SpringBasic.OnlyJava.order.OrderService;
import com.example.SpringBasic.OnlyJava.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean
    private MemberRepository getMemberRepository() {

        return new MemoryMemberRepositoryImpl();
    }
    @Bean
    private DiscountPolicy getDiscountPolicy(){

        return new FixDiscountPolicy();
    }
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy() );
    }

}
