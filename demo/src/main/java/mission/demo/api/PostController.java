package mission.demo.api;


import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.PostRequestDto;
import mission.demo.api.dto.PostResponseDto;
import mission.demo.service.PostService;
import mission.demo.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;


    /**
     * 게시물 전체 조회
     * @return PostResponseDto 리스트 반환
     */
    @GetMapping
    public ApiResponse<List<PostResponseDto>> findAllPost(){
        return ApiResponse.success("post-list", postService.findPostAll());
    }

    /**
     * 게시물을 등록한다.
     * @param requestDto 게시물을 등록하기 위한 제목, 내용
     * @return
     * @throws Exception
     */

    @PostMapping()
    public  ApiResponse<Long> uploadPost(@ModelAttribute PostRequestDto requestDto ) throws Exception {
        return ApiResponse.success("post-upload", postService.uploadPost(requestDto));
    }

    /**
     * 리뷰를 수정한다.
     * 리뷰 수정은 내용만 수정할 수 있다.
     * @param postId 수정할 게시물의 id
     * @param postRequestDto 수정할 게시물의 제목, 내용
     * @return updatePostId 수정한 게시물의 Id
     */

    @PutMapping("/{postId}")
    public ApiResponse<Long> updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto){
        Long updatePostId = postService.updatePost(postId, postRequestDto);
        return ApiResponse.success("post-update", updatePostId);
    }

    /**
     * 리뷰를 삭제한다.
     * @param postId 삭제할 게시물의 id
     * @return
     */

    @DeleteMapping("/{postId}")
    public ApiResponse<Long> deletePost(@PathVariable Long postId ){
        postService.deletePost(postId);
        return ApiResponse.success("post-delete", postId);
    }
}
