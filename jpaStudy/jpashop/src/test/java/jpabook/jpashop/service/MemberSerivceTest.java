package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberSerivceTest {

    @Autowired MemberSerivce memberSerivce;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입()throws Exception{

        Member member = new Member();
        member.setName("kim");

        Long savedId = memberSerivce.join(member);

        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{

        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberSerivce.join(member1);
        memberSerivce.join(member2);

        fail("에러가 발생해야 한다");
    }

}