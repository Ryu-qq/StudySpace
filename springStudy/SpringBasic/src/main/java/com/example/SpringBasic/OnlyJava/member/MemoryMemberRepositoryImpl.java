package com.example.SpringBasic.OnlyJava.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepositoryImpl implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findMember(Long Id) {
        return store.get(Id);
    }
}
