package com.example.SpringBasic.OnlyJava.member;

public interface MemberRepository {

    void save(Member member);

    Member findMember(Long Id);
}
