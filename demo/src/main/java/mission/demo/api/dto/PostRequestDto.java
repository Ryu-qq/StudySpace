package mission.demo.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import org.apache.catalina.Store;

@Data
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String contents;
    private String account_id;

    public PostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public PostRequestDto(String title, String contents, String account_id) {
        this.title = title;
        this.contents = contents;
        this.account_id = account_id;
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
