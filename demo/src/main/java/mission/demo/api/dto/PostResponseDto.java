package mission.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import mission.demo.domain.AccountType;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String nickname;
    private String accountType;
    @JsonIgnore
    private AccountType account_type;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long likeCount;
    private List<LikeListResponseDto> likeStatue;
    private List<LikeResponseDto> likeWho;


    public PostResponseDto(Long id, String title, String contents, String nickname, AccountType account_type, LocalDateTime createdDate, LocalDateTime modifiedDate, Long likeCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.accountType = account_type.getTitle();
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.likeCount = likeCount;
    }

    public PostResponseDto(Long id, String title, String contents, String nickname, AccountType account_type, LocalDateTime createdDate, LocalDateTime modifiedDate, Long likeCount, List<LikeListResponseDto> likeStatue) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.accountType = account_type.getTitle();
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.likeCount = likeCount;
        this.likeStatue = likeStatue;
    }


}
