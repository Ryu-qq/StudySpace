package mission.demo.service;

import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.LikeResponseDto;
import mission.demo.domain.Likes;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import mission.demo.domain.repository.LikeRepository;
import mission.demo.domain.repository.PostRepository;
import mission.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    /**
     *
     * @param postId 좋아요 할 게시물 아이디
     * @param userId 회원의 아이디
     * @return 기존의 좋아요를 누른 게시물이라면 좋아요를 취소, 아니라면 좋아요 실행 후 그때의 id(pk)값 반환
     */

    public Long checkLikeStatus(Long postId, Long userId){

        User user =userRepository.findById(userId).get();
        Likes likes = likeRepository.findByPostIdAndUserId(postId, userId);
        Post post =postRepository.findById(postId).get();

        return likes != null ? unlike(likes.getId()) : like(post, user);
    }

    public Long like(Post post, User user){
        return likeRepository.save(new Likes(post, user)).getId();
    }

    public Long unlike(Long likeId){
        likeRepository.deleteById(likeId);
        return -1L;
    }



}
