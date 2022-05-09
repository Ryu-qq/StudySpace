package mission.demo.domain.repository;

import mission.demo.api.dto.LikeResponseDto;
import mission.demo.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    /**
     * @return 포스트별 postId와 좋아요 수 반환
     */
    @Query("select new mission.demo.api.dto.LikeResponseDto(p.id , count(p.id)) from Likes l left join l.post p group by p.id")
    List<LikeResponseDto> getLikeCount();

}
