package com.example.SpringBasic;

public class StateFulService {


    public int order(String name, int price){
        System.out.println("name = " + name + "price =" + price);
        return price;
    }

}
