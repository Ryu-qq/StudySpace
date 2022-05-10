package mission.demo.config.auth;

import lombok.Getter;
import mission.demo.domain.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String nickname;
    private String account_id;
    private String accountType;


    public SessionUser(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.account_id = user.getAccount_id();
        this.accountType = user.getAccount_type().getKey();

    }
}
