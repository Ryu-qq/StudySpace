package mission.demo.config.auth;

import lombok.RequiredArgsConstructor;
import mission.demo.config.UserPrincipalService;
import mission.demo.util.HeaderUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SecurityAuthenticationFilter extends OncePerRequestFilter {


    private final UserPrincipalService userPrincipalService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accountID = HeaderUtil.getAccessToken(request);
        UserDetails authentication = userPrincipalService.loadUserByUsername(accountID);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getUsername(), null, authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);


        filterChain.doFilter(request, response);
    }
}
