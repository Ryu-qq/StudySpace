package mission.demo.domain.repository;

import mission.demo.api.dto.LikeResponseDto;
import mission.demo.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    Likes findByPostIdAndUserId(Long postId, Long userId);

    @Query("select new mission.demo.api.dto.LikeResponseDto( l.post.id, u.nickname, u.account_type, u.quit ,l.createdDate) from Likes l left join l.user u where l.post.id =:postId")
    List<LikeResponseDto> findByPost(@Param("postId") Long postId);




}
