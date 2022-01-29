package com.ryu.pt.springboot.web.domain.posts;

import com.ryu.pt.springboot.domain.posts.Posts;
import com.ryu.pt.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositroyTest {


    @Autowired
    PostsRepository postsRepository;
    //Junit 에서 단위테스트 끝날 떄 마다 수행되는 메소드를 지정

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
     public void board_Bringup(){
        String title = "테스트 게시글";
        String content = "게시글 본문";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("fbtkdals2@naver.com")
        .build());


        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


    @Test
    public void BaseTimeEntity_regist(){

        //given
        LocalDateTime now = LocalDateTime.of(2021,4,21,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createDate =" + posts.getCreatedDate()+", modifiedDate =" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
