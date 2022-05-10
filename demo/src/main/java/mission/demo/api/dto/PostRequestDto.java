package mission.demo.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import org.apache.catalina.Store;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PostRequestDto {

    @Size(min =1 , max = 15, message = "가게 글자 수는 최소 1자 이상,  최대 15자 이내입니다.")
    private String title;
    @Size(min =1 , max = 15, message = "본문 내용은 1자 이상,  최대 15자 이내입니다.")
    private String contents;

    public PostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Post toEntity(PostRequestDto requestDto, User user){

        return Post.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getTitle())
                .useYn("Y")
                .user(user)
                .build();
    }

}
