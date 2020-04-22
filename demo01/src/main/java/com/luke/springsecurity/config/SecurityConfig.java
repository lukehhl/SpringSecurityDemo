package com.luke.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")//拥有特定角色的用户才能访问特定路径；
                .antMatchers("/user/**").access("hasRole('admin') and hasRole('user')")//用.access来配置要同时拥有admin和user角色才能访问
                .anyRequest().authenticated()//除了以上的请求，剩下的请求只要经过了认证都能访问；
                .and()
                .formLogin()//可以通过表单提交来登录
                .loginProcessingUrl("/doLogin")//配置请求验证用户信息的url地址
//                .loginPage("/login")//配置默认的登录页面，代替自带的登录页面
                .usernameParameter("name")//配置请求用户名参数名
                .passwordParameter("password")//配置请求密码参数名
                .permitAll()//登录页面允许所有请求访问
                .successHandler(new AuthenticationSuccessHandler() {//配置登录成功后的处理
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp
                            , Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("status", 200);
                        //authentication.getPrincipal()获得登录后的主体
                        map.put("msg", authentication.getPrincipal());
                        //jackson提供的方法，将任何java对象序列化成json
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {//配置登录失败后的处理
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("status", 500);
                        if (e instanceof LockedException)
                            map.put("msg", "账户被锁定");
                        else if (e instanceof BadCredentialsException)
                            map.put("msg", e.getMessage() + "用户名或者密码错误");
                        else if (e instanceof DisabledException)
                            map.put("msg", "账户未启用");
                        else if (e instanceof AccountExpiredException)
                            map.put("msg", "账户过期");
                        else if (e instanceof CredentialsExpiredException)
                            map.put("msg", "密码过期");
                        else map.put("msg", "登录失败");

                        //jackson提供的方法，将任何java对象序列化成json
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .logout()
                .logoutUrl("/logout")//请求地址
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<Object, Object> map = new HashMap<>();
                        map.put("status", 200);
                        //authentication.getPrincipal()获得登录后的主体
                        map.put("msg", "注销成功");
                        //jackson提供的方法，将任何java对象序列化成json
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .csrf().disable();//如果需要用postman测试接口，这里关闭csrf保护

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//在内存中配置用户信息,在spring5之后，以下密码必须经过加密，否则出错
                .withUser("luke").password(passwordEncoder().encode("123")).roles("admin")
                .and()
                .withUser("java").password(passwordEncoder().encode("456")).roles("user");
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
