package com.luke.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MultiHttpSecurityConfig {

//    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//在内存中配置用户信息,在spring5之后，以下密码必须经过加密，否则出错
                .withUser("luke").password(passwordEncoder().encode("123")).roles("admin")
                .and()
                .withUser("java").password(passwordEncoder().encode("456")).roles("user");
    }

//    @Configuration
    @Order(1) //指定该配置的优先级
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");
        }
    }

//    @Configuration
    @Order(2)
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()//可以通过表单提交来登录
                    .loginProcessingUrl("/doLogin")//配置请求验证用户信息的url地址
//                    .loginPage("/login")//配置默认的登录页面，代替自带的登录页面
                    .usernameParameter("name")//配置请求用户名参数名
                    .passwordParameter("password")//配置请求密码参数名
                    .permitAll()//登录页面允许所有请求访问;
                    .and().csrf().disable();
        }
    }
}
