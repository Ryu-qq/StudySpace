package com.example.SpringBasic.OnlyJava.member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
