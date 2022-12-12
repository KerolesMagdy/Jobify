package com.keroles.jobify.Model.Custom;

import com.keroles.jobify.Model.Entity.Employer;
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
    @Getter
    private Employer employer;
    private char[] username;
    private char[] password;
    private boolean enable;
    private boolean expired;
    private boolean locked;
    private Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

    public UsersDetails(Users users) {
        if (users.getUserRoles()!=null)
            users.getUserRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(String.valueOf(userRole.getRoleName()))));
        this.password=users.getPassword();
        this.username=users.getEmail();
        this.enable=users.isEnabled();
        this.expired=users.isExpired();
        this.locked=users.isLocked();
        this.users=users;
    }

    public UsersDetails(Employer employer) {
        if (employer.getUserRoles()!=null)
            employer.getUserRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(String.valueOf(userRole.getRoleName()))));
        this.password=employer.getPassword();
        this.username=employer.getEmail();
        this.enable=employer.isEnabled();
        this.expired=employer.isExpired();
        this.locked=employer.isLocked();
        this.employer=employer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return String.valueOf(password);
    }

    @Override
    public String getUsername() {
        return String.valueOf(username);
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
