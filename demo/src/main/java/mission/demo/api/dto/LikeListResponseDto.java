package mission.demo.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LikeListResponseDto {

    private Long postId;
    private Long userId;
    private LocalDateTime createdDate;

    public LikeListResponseDto(Long postId, Long userId, LocalDateTime createdDate) {
        this.postId = postId;
        this.userId = userId;
        this.createdDate = createdDate;
    }

}
