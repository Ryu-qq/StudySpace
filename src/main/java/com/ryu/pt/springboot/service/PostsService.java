package com.ryu.pt.springboot.service;

import com.ryu.pt.springboot.domain.posts.Posts;
import com.ryu.pt.springboot.domain.posts.PostsRepository;
import com.ryu.pt.springboot.web.dto.PostsResponseDto;
import com.ryu.pt.springboot.web.dto.PostsSaveRequestDto;
import com.ryu.pt.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//final 이 선언된 모든 필드를 인자값으로 하는 생성자 생성
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음. id =" + id));

        return new PostsResponseDto(entity);
    }


}
