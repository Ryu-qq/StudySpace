package mission.demo.domain.repository;

import mission.demo.api.dto.PostResponseDto;
import mission.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * @return 전체 게시물 조회
     */
    @Query("select new mission.demo.api.dto.PostResponseDto(p.id, p.title, p.contents, u.nickname, u.account_type, p.createdDate, p.modifiedDate, count(l.post.id)) from Post p left join p.user u left join p.likes l where p.useYn='Y' and u.quit =false group by p.id")
    List<PostResponseDto> findPostAll();


    /**
     * 게시물 단건 조회
     * @param postId
     */
    @Query("select new mission.demo.api.dto.PostResponseDto(p.id, p.title, p.contents, u.nickname, u.account_type, p.createdDate, p.modifiedDate, count(l.post.id)) from Post p left join p.user u left join p.likes l where p.id =:postId and p.useYn='Y' and u.quit =false group by p.id")
    Optional<PostResponseDto> findPost(@Param("postId") Long postId);

    /**
     * 삭제와 수정을 위한 조회
     * @param postId 삭제 또는 수정할 게시물 아이디
     * @param userId 위의 행위를 하려는 유저의 아이디
     */
    @Query("select p from Post p left join p.user u where p.id =:postId and u.id=:userId and p.useYn='Y' and u.quit =false")
    Optional<Post> findByPostIdAndUserId(@Param("postId") Long postId,@Param("userId")  Long userId);
}

