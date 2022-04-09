package com.example.SpringBasic.OnlyJava;

import com.example.SpringBasic.OnlyJava.discount.DiscountPolicy;
import com.example.SpringBasic.OnlyJava.discount.FixDiscountPolicy;
import com.example.SpringBasic.OnlyJava.member.MemberRepository;
import com.example.SpringBasic.OnlyJava.member.MemoryMemberRepositoryImpl;
import com.example.SpringBasic.OnlyJava.member.MemberService;
import com.example.SpringBasic.OnlyJava.member.MemberServiceImpl;
import com.example.SpringBasic.OnlyJava.order.OrderService;
import com.example.SpringBasic.OnlyJava.order.OrderServiceImpl;

public class AppConfig {


    private MemberRepository getMemberRepository() {
        return new MemoryMemberRepositoryImpl();
    }

    public DiscountPolicy getDiscountPolicy(){
        return new FixDiscountPolicy();
    }

    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }


    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy() );
    }

}
