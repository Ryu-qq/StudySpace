package mission.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import mission.demo.api.dto.PostRequestDto;
import mission.demo.api.dto.PostResponseDto;
import mission.demo.domain.Post;
import mission.demo.domain.repository.PostRepository;
import mission.demo.service.PostService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String title = "title";
        String contents = "content";
        PostRequestDto requestDto = new PostRequestDto("테스트", "게스트빼고 모두 글 작성 가능");

        String url = "http://localhost:" + port + "/api/post";

        //when
        //회원일때
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", title)
                .param("contents", contents)
                .header(HttpHeaders.AUTHORIZATION, "LESSOR kim123")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //게스트일때
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", title)
                .param("contents", contents)
                .header(HttpHeaders.AUTHORIZATION, "")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());

        //then
        List<PostResponseDto> all = postRepository.findPostAll();
        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(title);
        assertThat(all.get(all.size()-1).getContents()).isEqualTo(contents);

    }

    @Test
    public void Posts_수정된다() throws Exception {

        //given
        String title = "수정";
        String contents = "완료";
        String originTitle ="";
        String originContents ="";
        Long postId = null;

        PostRequestDto requestDto = new PostRequestDto("수정", "완료");


        //LESSOR kim123의 userId =4L 이다  initDb 클래스 또는 H2 콘솔창에서 확인 가능
        List<Post> postList = postRepository.findByUserId(4L);

        if(postList.size() >0){
            originTitle = postList.get(0).getTitle();
            originContents = postList.get(0).getContents();
            postId = postList.get(0).getId();
        }

        String url = "http://localhost:" + port + "/api/post/" + postId;

        //when
        //회원일때
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", title)
                .param("contents", contents)
                .header(HttpHeaders.AUTHORIZATION, "LESSOR kim123")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //회원이지만 자신이 작성한 글이 아닐때
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", title)
                .param("contents", contents)
                .header(HttpHeaders.AUTHORIZATION, "LESSEE ryu")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());


        //게스트일때
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", title)
                .param("contents", contents)
                .header(HttpHeaders.AUTHORIZATION, "")
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());

        //then

        Optional<PostResponseDto> findPost = postRepository.findByPostId(postId);


        assertThat(findPost.get().getTitle()).isNotSameAs(originTitle);
        assertThat(findPost.get().getContents()).isNotSameAs(originContents);

        assertThat(findPost.get().getTitle()).isEqualTo(title);
        assertThat(findPost.get().getContents()).isEqualTo(contents);
    }

    @Test
    public void Posts_삭제된다() throws Exception {

        //given
        Long postId = null;

        //LESSOR kim123의 userId =4L 이다  initDb 클래스 또는 H2 콘솔창에서 확인 가능
        List<Post> postList = postRepository.findByUserId(4L);

        if(postList.size() >0){
            postId = postList.get(0).getId();
        }

        String url = "http://localhost:" + port + "/api/post/" + postId;

        //when
        //회원일때
        mvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "LESSOR kim123")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isOk());

        //회원이지만 자신이 작성한 글이 아닐때
        mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "LESSEE ryu")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isForbidden());

        //게스트일때
        mvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isForbidden());

        //then
        List<Post> modifiedPostList = postRepository.findByUserId(4L);

        //지운 게시물의 useYn 이 N으로 바뀜을 예상
        assertThat(modifiedPostList.get(0).getUseYn()).isEqualTo("N");
    }


    @Test
    public void Posts_좋아요() throws Exception {

        //given

        Long postId = null;
        //REALTOR park123 userId =5L 이다  initDb 클래스 또는 H2 콘솔창에서 확인 가능
        List<Post> postList = postRepository.findByUserId(5L);

        if(postList.size() >0){
            postId = postList.get(0).getId();
        }
        Long likeCount = postRepository.findByPostId(postId).get().getLikeCount();


        String url = "http://localhost:" + port + "/api/post/" + postId + "/likes";


        //when
        //LESSOR kim123 userId = 4L 로 park123 게시물을 좋아요 해보겠다.
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "LESSOR kim123")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isOk());

        //비회원은 좋아요 기능을 사용할 수 없다.
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isForbidden());

        Optional<PostResponseDto> likePost = postRepository.findByPostId(postId);


        //then
        Long modifiedLikeCount = postRepository.findByPostId(postId).get().getLikeCount();


        //기존보다 좋아요 수가 1 증가
        assertThat(likeCount).isEqualTo(modifiedLikeCount-1);

        //when
        //LESSOR kim123 userId = 4L 로 park123 게시물을  한번 더 좋아요 해보겠다.
        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.AUTHORIZATION, "LESSOR kim123")
                .content(new ObjectMapper().writeValueAsString("")))
                .andExpect(status().isOk());

        Long backLikeCount = postRepository.findByPostId(postId).get().getLikeCount();

        //then
        //좋아요 상태에서 좋아요를 또 누르면 좋아요 취소가 된다.
        assertThat(likeCount).isEqualTo(backLikeCount);


    }


}