package mission.demo.api;


import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.PostRequestDto;
import mission.demo.api.dto.PostResponseDto;
import mission.demo.config.auth.LoginUser;
import mission.demo.config.auth.SessionUser;
import mission.demo.service.LikeService;
import mission.demo.service.PostService;
import mission.demo.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    /**
     * 게시물 전체 조회
     * @return PostResponseDto 리스트 반환
     */
    @GetMapping
    public ApiResponse<List<PostResponseDto>> findAllPost(@LoginUser SessionUser user){
        return ApiResponse.success("post-list", postService.findPostAll(user.getId()));
    }

    /**
     * @param postId 조회 할 게시물 아이디
     * @return 게시물의 정보와 좋아요를 누른 사람들
     */

    @GetMapping({"/{postId}"})
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId){
        return ApiResponse.success("post", postService.findPost(postId));
    }

    /**
     * 게시물을 등록한다.
     * @param requestDto 게시물을 등록하기 위한 제목, 내용
     * @param user 로그인한 사용자의 정보
     * @return 저장한 게시물의 Id
     * @throws Exception
     */

    @PostMapping()
    public  ApiResponse<Long> uploadPost(@ModelAttribute @Valid PostRequestDto requestDto, @LoginUser SessionUser user ) throws Exception {
        return ApiResponse.success("post-upload", postService.uploadPost(requestDto, user.getId()));
    }

    /**
     * 리뷰를 수정한다.
     * 리뷰 수정은 내용만 수정할 수 있다.
     * @param postId 수정할 게시물의 id
     * @param postRequestDto 수정할 게시물의 제목, 내용
     * @param user 로그인한 사용자의 정보
     * @return updatePostId 수정한 게시물의 Id
     */

    @PutMapping("/{postId}")
    public ApiResponse<Long> updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto, @LoginUser SessionUser user){
        return ApiResponse.success("post-update", postService.updatePost(postId, postRequestDto, user.getId()));
    }

    /**
     * 리뷰를 삭제한다.
     * @param postId 삭제할 게시물의 id
     * @param user 로그인한 사용자의 정보
     * @return 삭제한 게시물의 Id
     */

    @DeleteMapping("/{postId}")
    public ApiResponse<Long> deletePost(@PathVariable Long postId , @LoginUser SessionUser user){
        return ApiResponse.success("post-delete",  postService.deletePost(postId, user.getId()));
    }

    /**
     * @param postId 좋아요 또는 좋아요 취소 할 게시물 아이디
     * @param user 로그인한 사용자의 정보
     */
    @PostMapping("/{postId}/likes")
    public ApiResponse<Long> likes(@PathVariable Long postId, @LoginUser SessionUser user){
        return ApiResponse.success("post-like",  likeService.checkLikeStatus(postId, user.getId()));
    }

}
