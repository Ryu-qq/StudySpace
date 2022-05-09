package mission.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mission.demo.domain.AccountType;

import java.time.LocalDateTime;

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
    private Long count;


    public PostResponseDto(Long id, String title, String contents, String nickname, AccountType account_type, String accountType, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.accountType = accountType;
        this.account_type = account_type;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
