package com.ryu.pt.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//DB Layer  JapRepository<Entity class, PK type> 하면 CRUD 저절로 생성
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
