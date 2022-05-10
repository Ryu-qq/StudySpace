package mission.demo.domain.repository;

import mission.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.account_id =:accountId")
    Optional<User> findByAccountId(@Param("accountId") String account_id);

}
