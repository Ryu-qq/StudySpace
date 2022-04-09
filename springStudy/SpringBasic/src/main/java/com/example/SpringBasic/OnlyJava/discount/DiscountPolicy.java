package com.example.SpringBasic.OnlyJava.discount;

import com.example.SpringBasic.OnlyJava.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
