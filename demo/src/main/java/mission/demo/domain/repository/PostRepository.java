package mission.demo.domain.repository;

import mission.demo.api.dto.PostResponseDto;
import mission.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /*
     * @return 전체 포스트 조회
     */
    @Query("select new mission.demo.api.dto.PostResponseDto(p.id, p.title, p.contents, u.nickname, u.account_type, p.createdDate, p.modifiedDate) from Post p left join p.user u where p.useYn='Y'")
    List<PostResponseDto> findPostAll();
}

