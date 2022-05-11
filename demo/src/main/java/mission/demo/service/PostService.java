package mission.demo.service;

import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.LikeListResponseDto;
import mission.demo.api.dto.LikeResponseDto;
import mission.demo.api.dto.PostRequestDto;
import mission.demo.api.dto.PostResponseDto;
import mission.demo.domain.Likes;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import mission.demo.domain.repository.LikeRepository;
import mission.demo.domain.repository.PostRepository;
import mission.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;


    /**
     * findPostAll로 게시물 전체 가져옴
     * 로그인 상태일때 좋아요한 게시물 확인 가능
     * @return 전체 게시물 반환
     */
    public List<PostResponseDto> findPostAll(Long userId){
        List<PostResponseDto> postAll = postRepository.findPostAll();

        if(userId !=null){
            List<LikeListResponseDto> likesList = likeRepository.findLikeByUserId(userId);
            if(likesList.size() <=0) return postAll;

            Map<Long, List<LikeListResponseDto>> groupByPost = likesList.stream()
                    .collect(Collectors.groupingBy(LikeListResponseDto -> LikeListResponseDto.getPostId()));

            postAll.forEach(o -> o.setLikeStatue(groupByPost.get(o.getId())));
        }

        return postAll;

    }

    /**
     * @param postId 조회할 게시물
     * @return 게시물 정보와 그 게시물을 좋아요 한 사람들 정보
     */
    public PostResponseDto findPost(Long postId){
        PostResponseDto post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. 다시 한번 확인해주세요. id=" + postId));
        List<LikeResponseDto> likeList = likeRepository.findLikeByPostId(post.getId());
        post.setLikeWho(likeList);


        return post;
    }

    /**
     * 게시물을 업로드한다.
     * 회원만 작성할 수 있다.
     * @param requestDto 게시물 제목, 내용
     * @param userId 게시물을 올리는 유저
     * @return
     * @throws Exception
     */

    @Transactional
    public Long uploadPost(PostRequestDto requestDto, Long userId) throws Exception {

        User user = userRepository.findById(userId).get();
        Post post = requestDto.toEntity(requestDto, user);

        return postRepository.save(post).getId();

    }



    /**
     * @param postId 수정할 게시물의 포스트
     * @param postRequestDto 수정할 게시물의 제목, 내용
     * @param userId 게시물을 수정하는 유저
     * @return 수정한 게시물 아이디
     */
    @Transactional
    public Long updatePost(Long postId, PostRequestDto postRequestDto, Long userId){

        Post posts = postRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + postId));
        posts.update(postRequestDto.getTitle(), postRequestDto.getContents());

        return postId;

    }

    /**
     * 리뷰를 삭제한다.
     * @param postId 삭제할 게시물
     * @return 삭제한 게시물 아이디
     */

    @Transactional
    public Long deletePost(Long postId,  Long userId){
        Post posts = postRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + postId));

        posts.delete("N");
        return postId;
    }
}
