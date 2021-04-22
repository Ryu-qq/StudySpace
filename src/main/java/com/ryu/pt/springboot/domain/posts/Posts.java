package com.ryu.pt.springboot.domain.posts;

import com.ryu.pt.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//실제 데이터베이스의 테이블과 매칭될 클래스
@Entity//JPA
@Getter
@NoArgsConstructor//기본 생성자 추가
//public Posts(){} 역할
public class Posts extends BaseTimeEntity {
    @Id//해당 테이블의 PK필드, PK생성규칙
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
