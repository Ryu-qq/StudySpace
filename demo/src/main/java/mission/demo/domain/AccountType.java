package mission.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AccountType {

    LESSOR("ROLE_LESSOR", "임대인"),
    REALTOR("ROLE_REALTOR", "공인중개사"),
    LESSEE("ROLE_LESSEE", "임차인"),
    GUEST("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;

}
