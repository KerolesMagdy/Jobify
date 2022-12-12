package com.keroles.jobify.Sec.Authentication;

import com.keroles.jobify.Model.Custom.EmployerUserDetails;
import com.keroles.jobify.Model.Custom.UsersDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmployerPassAuthToken extends UsernamePasswordAuthenticationToken {

    @Getter
    @Setter
    private EmployerUserDetails employerUserDetails;

    public EmployerPassAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public EmployerPassAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
