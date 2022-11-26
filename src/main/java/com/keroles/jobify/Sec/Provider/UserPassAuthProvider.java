package com.keroles.jobify.Sec.Provider;

import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import com.keroles.jobify.Service.Implementation.UserService;
import com.keroles.jobify.Model.UsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserPassAuthProvider implements AuthenticationProvider {


    @Autowired
    UserService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserPassAuthToken authenticate(Authentication authentication) throws AuthenticationException {
        String username =authentication.getName();
        String password=(String)authentication.getCredentials();

        UserPassAuthToken userPassAuthToken;
        UsersDetails   user=userDetailsService.loadUserByUsername(username);
        if (user !=null && password != null && passwordEncoder.matches(password,user.getPassword())){
            userPassAuthToken=new UserPassAuthToken(username,user.getPassword(),user.getAuthorities());
            userPassAuthToken.setUsersDetails(user);
        }else {
            userPassAuthToken = new UserPassAuthToken(username, password);
            userPassAuthToken.setAuthenticated(false);
        }
        return userPassAuthToken;
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UserPassAuthToken.class.equals(authType);
    }
}
