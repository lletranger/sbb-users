package org.tsys.sbb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("org.tsys.sbb")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/info**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/ticket/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/mytickets").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/annulticket/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/search**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/index").permitAll()
                .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");
        http.logout()
                .permitAll()
                .clearAuthentication(true)
                .invalidateHttpSession(true);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}