package mission.demo.config;

import lombok.RequiredArgsConstructor;
import mission.demo.config.auth.SessionMember;
import mission.demo.domain.AccountType;
import mission.demo.domain.Member;
import mission.demo.domain.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        String guestName = "GUEST" + UUID.randomUUID();
        Member member = memberRepository.findMemberByAccountId(accountId).orElseGet(() -> new Member(guestName, AccountType.GUEST));
        httpSession.setAttribute("member", new SessionMember(member));
        return new UserPrincipal(member);

    }
}
