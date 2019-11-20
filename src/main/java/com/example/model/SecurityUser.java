package com.example.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUser extends User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> authorities;
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SecurityUser(String username,String password,Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
        this.username =username;
        this.setLoginname(username);
        this.setPassword(password);
        this.setAuthorities(authorities);
    }

    /**
     * 账户是否过期
     * 
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * 
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * 
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * 
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }


}
