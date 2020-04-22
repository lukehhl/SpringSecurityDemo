package com.luke.springsecurity.config;

import com.luke.springsecurity.entity.Menu;
import com.luke.springsecurity.entity.Role;
import com.luke.springsecurity.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    private final MenuService menuService;
    private final AntPathMatcher antPathMatcher;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取用户在前端访问的url路径
        String url = ((FilterInvocation) object).getRequestUrl();
        System.out.println(url);
        List<Menu> menus = menuService.queryALlWithRoles();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getPath(), url)) {
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) rolesStr[i] = roles.get(i).getName();
//                System.out.println(rolesStr[0]);
                //将得到的role集合返回出去
                return SecurityConfig.createList(rolesStr);
            }
        }
        //如果访问的url路径没有在限制的资源路径里，则指定一个特殊角色使其可以访问非限制的资源
        return SecurityConfig.createList("ROLE_GUEST");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


}
