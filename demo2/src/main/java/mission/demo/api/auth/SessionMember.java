package mission.demo.api.auth;

import lombok.Getter;
import mission.demo.domain.Member;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private Long id;
    private String name;

    public SessionMember(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }
}
