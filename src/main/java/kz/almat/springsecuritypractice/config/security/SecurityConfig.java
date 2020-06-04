package kz.almat.springsecuritypractice.config.security;

import kz.almat.springsecuritypractice.config.security.permission.TweetPermission;
import kz.almat.springsecuritypractice.config.security.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static kz.almat.springsecuritypractice.config.security.role.Role.ADMIN;
import static kz.almat.springsecuritypractice.config.security.role.Role.USER;

/**
 * @author Almat on 04.06.2020
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/tweets/**").hasAuthority(TweetPermission.TWEET_READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/api/tweets/**").hasAuthority(TweetPermission.TWEET_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/tweets/**").hasAuthority(TweetPermission.TWEET_UPDATE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/api/tweets/**").hasAuthority(TweetPermission.TWEET_DELETE.getPermission())
//                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userAlmat = User.builder()
                .username("almat")
                .password(passwordEncoder.encode("almat"))
                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userAlmat,
                userAdmin
        );
    }
}
