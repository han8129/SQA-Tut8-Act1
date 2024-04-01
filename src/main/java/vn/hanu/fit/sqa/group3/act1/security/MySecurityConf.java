package vn.hanu.fit.sqa.group3.act1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import vn.hanu.fit.sqa.group3.act1.service.UserService;

@Configuration
@EnableWebSecurity
public class MySecurityConf {
    @Bean public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);

        return auth;
    }

    @Bean SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception{
        return http
            .authorizeHttpRequests(req -> req
                .requestMatchers("/registration**")
                .permitAll()
                .anyRequest() .authenticated()
            )
            .authenticationProvider(authenticationProvider)
            .build();
    }


    @Autowired private UserService userService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
}
