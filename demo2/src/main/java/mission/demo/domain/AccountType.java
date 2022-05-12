package mission.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    MEMBER("ROLE_MEMBER", "회원"),
    GUEST("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;

}
