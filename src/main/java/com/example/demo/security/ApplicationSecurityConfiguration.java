package com.example.demo.security;

import com.example.demo.security.jwt.JwtConfig;
import com.example.demo.security.jwt.JwtTokenVerifier;
import com.example.demo.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtUsernameAndPasswordAuthenticationFilter jwtUsernameAndPasswordAuthenticationFilter = new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig);
        jwtUsernameAndPasswordAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtUsernameAndPasswordAuthenticationFilter)
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/api/login/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/", "index", "css/**", "js/**").permitAll()
//                .antMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated();
//                .and()
//                .httpBasic();
    }
}
