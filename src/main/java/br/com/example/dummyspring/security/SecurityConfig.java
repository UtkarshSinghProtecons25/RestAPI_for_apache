package br.com.example.dummyspring.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationFilter f;

    public SecurityConfig(JwtAuthenticationFilter f) {
        this.f = f;
    }

    protected void configure(HttpSecurity h) throws Exception {
        h.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/h2-console/**").permitAll().anyRequest().authenticated().and().headers().frameOptions().disable();
        h.addFilterBefore(f, UsernamePasswordAuthenticationFilter.class);
    }
}
