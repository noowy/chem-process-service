package com.technolog.chemprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "chem-process.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private UserDetailsService userService;

    private final String encoderSecret = "";

    @Autowired
    public SecurityConfig(UserDetailsService userService)
    {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder encoder()
    {
        return new StandardPasswordEncoder(encoderSecret);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .userDetailsService(userService)
            .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api").permitAll()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers("/process")
                    .hasRole("USER")
                .antMatchers("/", "/register").permitAll()
            .and()
                .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/process")
                    .failureUrl("/?error=true")
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .csrf().disable();
    }
}
