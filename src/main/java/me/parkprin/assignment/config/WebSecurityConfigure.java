package me.parkprin.assignment.config;

import me.parkprin.assignment.security.EntryPointUnauthorizedHandler;
import me.parkprin.assignment.security.Jwt;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final Jwt jwt;

    private final EntryPointUnauthorizedHandler unauthorizedHandler;

    public WebSecurityConfigure(Jwt jwt, EntryPointUnauthorizedHandler unauthorizedHandler){
        this.jwt = jwt;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/initdata/**").permitAll()
                .antMatchers("/api/orders").permitAll()
                .antMatchers("/api/reviews").permitAll()
                .antMatchers("/api/products").permitAll()
                .antMatchers("/api/users").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .disable();

    }
}
