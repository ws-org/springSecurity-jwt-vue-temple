package com.example.security.jwt;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class JwtUser implements UserDetails {

    private String userid;

    private String username;

    private String password;

    private Integer state;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    public JwtUser(String userid,String username, String password, Integer state, Collection<? extends GrantedAuthority> authorities) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.state = state;
        this.authorities = authorities;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }



    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    //是否启用
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
