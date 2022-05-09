package mission.demo.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeResponseDto {
    private Long postId;
    private Long count;

    public LikeResponseDto(Long postId, Long count) {
        this.postId = postId;
        this.count = count;
    }
}
