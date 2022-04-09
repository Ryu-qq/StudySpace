package com.example.SpringBasic;

import com.example.SpringBasic.OnlyJava.AppConfig;
import com.example.SpringBasic.OnlyJava.member.Grade;
import com.example.SpringBasic.OnlyJava.member.Member;
import com.example.SpringBasic.OnlyJava.member.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}

}
