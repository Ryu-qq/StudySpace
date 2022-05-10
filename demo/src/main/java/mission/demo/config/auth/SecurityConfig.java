package mission.demo.config.auth;

import lombok.RequiredArgsConstructor;
import mission.demo.config.UserPrincipalService;
import mission.demo.config.auth.CustomAccessDeniedHandler;
import mission.demo.config.auth.SecurityAuthenticationFilter;
import mission.demo.domain.AccountType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final UserPrincipalService userPrincipalService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/post", "/api/post/**").permitAll()
                .antMatchers("/api/post/**").hasAnyAuthority(AccountType.LESSEE.getKey(),AccountType.LESSOR.getKey(),AccountType.REALTOR.getKey())
                .antMatchers("/api/post/**/likes").hasAnyAuthority(AccountType.LESSEE.getKey(),AccountType.LESSOR.getKey(),AccountType.REALTOR.getKey());

        http.addFilterBefore(securityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    /*
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * 필터 설정
     * */
    @Bean
    public SecurityAuthenticationFilter securityAuthenticationFilter() {
        return new SecurityAuthenticationFilter(userPrincipalService);
    }


}
