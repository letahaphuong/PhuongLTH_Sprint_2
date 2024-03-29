package com.example.phuonglth_sprint_2.config;

import com.example.phuonglth_sprint_2.jwt.jwt.JwtEntryPoint;
import com.example.phuonglth_sprint_2.jwt.jwt.JwtTokenFilter;
import com.example.phuonglth_sprint_2.jwt.userprincal.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountDetailService accountDetailService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("api/customer/**", "api/category/**", "api/product/**").access("hasRole('ADMIN')");
        httpSecurity.authorizeRequests().antMatchers("api/change-avatar", "api/product/**").access("hasAnyRole('ADMIN','USER')");
        httpSecurity.cors().and().csrf().disable()// huỷ CrossOrigin
                .authorizeRequests()
                .antMatchers( "api/public") // cho tất cả các role vào
                .permitAll()
                .anyRequest()
                .authenticated()// khi có account đăng nhập
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint) // lớp gác cổng
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
