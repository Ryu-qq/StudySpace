package com.example.SpringBasic.OnlyJava.discount;

import com.example.SpringBasic.OnlyJava.member.Grade;
import com.example.SpringBasic.OnlyJava.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
