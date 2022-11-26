package com.keroles.jobify.Model;

import com.keroles.jobify.Model.Entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UsersDetails implements UserDetails {

    @Getter
    private Users users;
    private String username;
    private String password;
    private boolean enable;
    private boolean expired;
    private boolean locked;
    private Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

    public UsersDetails(Users users) {
        if (users.getUserRoles()!=null)
            users.getUserRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRoleName())));
        this.password=users.getPassword();
        this.username=users.getEmail();
        this.enable=users.isEnabled();
        this.expired=users.isExpired();
        this.locked=users.isLocked();
        this.users=users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
