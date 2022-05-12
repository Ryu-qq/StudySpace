package mission.demo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mission.demo.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final String account_id;
    private final Collection<? extends GrantedAuthority> authorities;


    public UserPrincipal(Member member) {
        this.account_id = member.getAccount_id();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getAccount_type().getKey()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return account_id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
