package mission.demo.domain.repository;

import mission.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.account_id =:accountId")
    Optional<Member> findMemberByAccountId(@Param("accountId") String accountId);

}
