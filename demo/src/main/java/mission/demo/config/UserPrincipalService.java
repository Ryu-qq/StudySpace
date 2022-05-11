package mission.demo.config;

import lombok.RequiredArgsConstructor;
import mission.demo.config.auth.SessionUser;
import mission.demo.domain.AccountType;
import mission.demo.domain.User;
import mission.demo.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        String guestName = "GUEST" + UUID.randomUUID();
        User user = userRepository.findUserByAccountId(accountId).orElseGet(() -> new User(guestName, AccountType.GUEST));
        httpSession.setAttribute("user", new SessionUser(user));
        return new UserPrincipal(user);

    }
}
