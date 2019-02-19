package com.plumblum.config;

import com.plumblum.filter.ValidateCodeFilter;
import com.plumblum.properties.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetail userDetail;

    /**
     * 配置不拦截的url请求
     */
    @Autowired
    private SecurityProperty securityProperty;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//配置自定义过滤器，用于校验验证码是否正确
                .formLogin()
                .loginPage("/authentication/require")//如果需要身份认证，需要跳转到的页面或者请求
                .loginProcessingUrl("/authentication/form")//表单提交action
                .successHandler(authenticationSuccessHandler)//登录成功处理器
                .failureHandler(authenticationFailureHandler)//登录失败处理器
                .and().authorizeRequests()
                .antMatchers("/authentication/require",securityProperty.getBrowserProperty().getLoginPage(),"/code/image").permitAll()//配置不被拦截的请求
                .anyRequest().authenticated()
                .and().csrf().disable();//跨站请求保护

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail).passwordEncoder(new BCryptPasswordEncoder());
    }
}
