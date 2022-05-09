package mission.demo.service;

import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.LikeResponseDto;
import mission.demo.api.dto.PostRequestDto;
import mission.demo.api.dto.PostResponseDto;
import mission.demo.domain.Likes;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import mission.demo.domain.repository.LikeRepository;
import mission.demo.domain.repository.PostRepository;
import org.apache.catalina.Store;
import org.springframework.security.core.parameters.P;
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


    /**
     * findPostAll로 포스트 전체 가져옴
     * getCount로 포스트별 좋아요 수 가져옴
     *
     * postAll과 count있는 postId가 서로 같으면 좋아요 수 삽입 후 반환
     * @return
     */
    public List<PostResponseDto> findPostAll(){

        List<PostResponseDto> postList = postRepository.findPostAll();
        List<LikeResponseDto> countList = likeRepository.getLikeCount();

        for(LikeResponseDto responseDto : countList){
            for(int i=0; i<postList.size(); i++){
                if(postList.get(i).getId() == responseDto.getPostId()) postList.get(i).setCount(responseDto.getCount());
                postList.get(i).setAccountType(postList.get(i).getAccount_type().getTitle());
            }
        }

        return postList;
    }

    /**
     * 게시물을 업로드한다.
     * 회원만 작성할 수 있다.
     * @param requestDto 게시물 제목, 내용
     * @return
     * @throws Exception
     */

    @Transactional
    public Long uploadPost(PostRequestDto requestDto) throws Exception {

        User user = userService.getUser(requestDto.getAccount_id());
        Post post = requestDto.toEntity(requestDto, user);
        postRepository.save(post);

        return postRepository.save(post).getId();

    }



    /**
     * @param postId 수정할 게시물의 포스트
     * @param postRequestDto 수정할 게시물의 제목, 내용
     * @return 수정한 게시물 아이디
     */
    @Transactional
    public Long updatePost(Long postId, PostRequestDto postRequestDto){
        Post posts = postRepository.findById(postId)
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
    public Long deletePost(Long postId){
        Post posts = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + postId));

        posts.delete("N");
        return postId;
    }
}
