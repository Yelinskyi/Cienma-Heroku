package project.cinema.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.cinema.spring.model.RoleType;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/inject").permitAll()
                .antMatchers(HttpMethod.GET,"/cinema-halls").authenticated()
                .antMatchers(HttpMethod.POST,"/cinema-halls").hasRole(RoleType.ADMIN.toString())
                .antMatchers(HttpMethod.GET,"/movies/**").authenticated()
                .antMatchers(HttpMethod.POST,"/movies/**").hasRole(RoleType.ADMIN.toString())
                .antMatchers(HttpMethod.GET,"/movie-sessions/available").authenticated()
                .antMatchers(HttpMethod.GET,"/movie-sessions/{id}").authenticated()
                .antMatchers(HttpMethod.POST,"/movie-sessions").hasRole((RoleType.ADMIN.toString()))
                .antMatchers(HttpMethod.PUT,"/movie-sessions/{id}")
                        .hasRole((RoleType.ADMIN.toString()))
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/{id}")
                        .hasRole((RoleType.ADMIN.toString()))
                .antMatchers(HttpMethod.GET,"/orders").hasRole((RoleType.USER.toString()))
                .antMatchers(HttpMethod.POST,"/orders/complete").hasRole((RoleType.USER.toString()))
                .antMatchers(HttpMethod.PUT,"/shopping-carts/movie-sessions")
                        .hasRole((RoleType.USER.toString()))
                .antMatchers(HttpMethod.GET,"/shopping-carts/by-user")
                        .hasRole((RoleType.USER.toString()))
                .antMatchers(HttpMethod.GET,"/users/by-email").hasRole((RoleType.ADMIN.toString()))
                .antMatchers(HttpMethod.GET,"/cinema-halls/**").hasRole(RoleType.USER.toString())
                .antMatchers(HttpMethod.POST,"/cinema-halls/**").hasRole(RoleType.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
