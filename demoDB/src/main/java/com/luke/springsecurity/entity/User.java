package com.luke.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-21 19:41:07
 */
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = -29255401545561685L;

    private Integer id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private List<Role> roles;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            //这里添加的角色必须带有"ROLE_"，SpringSecurity才能正确识别角色
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    @Override//账户是否未过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//账户是否未锁定
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override//凭证(密码)是否未过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override//账户是否启用
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}