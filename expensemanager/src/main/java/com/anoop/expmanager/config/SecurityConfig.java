package com.anoop.expmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/6/17
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.anoop.expmanager")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("loginService")
    UserDetailsService loginService;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // .antMatchers("/app/**").permitAll()
                .antMatchers("/app/**").access("hasAnyAuthority('Admin','User','Manager','Tester','Developer','Reporter')")
                .antMatchers("/admin/**").access("hasAnyAuthority('Admin')")
//                .antMatchers("/app/**").access("hasRole('Administrator')")
//                .antMatchers("/app/**").access("hasRole('Tester')")
//                .antMatchers("/app/**").access("hasRole('Developer')")
                .and().formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
//                .deleteCookies("remove")
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder;
    }
}
