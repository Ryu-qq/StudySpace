package com.example.SpringBasic;

import com.example.SpringBasic.OnlyJava.AppConfig;
import com.example.SpringBasic.OnlyJava.AppSpringConfig;
import com.example.SpringBasic.OnlyJava.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppSpringConfig.class);

        MemberService service1 = applicationContext.getBean("memberService", MemberService.class);
        MemberService service2 = applicationContext.getBean("memberService", MemberService.class);

        System.out.println("service1 = " + service1);
        System.out.println("service2 = " + service2);

        Assertions.assertThat(service1).isSameAs(service2);

    }
}