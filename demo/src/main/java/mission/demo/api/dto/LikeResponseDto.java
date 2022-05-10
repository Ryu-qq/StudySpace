package mission.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import mission.demo.domain.AccountType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LikeResponseDto {

    private Long postId;
    private String nickname;
    private String accountType;
    @JsonIgnore
    private AccountType account_type;
    private boolean quit;
    private LocalDateTime createdDate;


    public LikeResponseDto(Long postId, String nickname, AccountType accountType, boolean quit, LocalDateTime createdDate) {
        this.postId = postId;
        this.nickname = nickname;
        this.accountType = accountType.getTitle();
        this.quit = quit;
        this.createdDate = createdDate;
    }
}
