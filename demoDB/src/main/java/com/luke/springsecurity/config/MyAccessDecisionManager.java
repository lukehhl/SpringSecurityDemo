package com.luke.springsecurity.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> resqAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        //获取当前登录了的用户的角色权限信息
        Collection<? extends GrantedAuthority> currentAuths = authentication.getAuthorities();
        for (ConfigAttribute attribute : resqAttributes) {
            //如果是可以访问非限制路径的游客角色ROLE_GUEST，再进行是否已经登录的判断
//            if ("ROLE_login".equals(attribute.getAttribute()))
                if (authentication instanceof AnonymousAuthenticationToken) {
                    System.out.println(authentication.getPrincipal().toString());
                    throw new AccessDeniedException("请登录");
                }
//                else return;

            for (GrantedAuthority authority : currentAuths) {
                System.out.println("需要角色" + attribute.getAttribute());
                System.out.println("当前角色" + authority.getAuthority());
                if (authority.getAuthority().equals(attribute.getAttribute())) {
                    System.out.println(authority.getAuthority());
                    return;
                }
            }

        }
        return;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
