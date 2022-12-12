package com.keroles.jobify.Sec.Provider;

import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import com.keroles.jobify.Service.Implementation.UserService;
import com.keroles.jobify.Model.Custom.UsersDetails;
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
        char[] username =authentication.getName().toCharArray();
        char[] password=authentication.getCredentials().toString().toCharArray();

        UserPassAuthToken userPassAuthToken;
        UsersDetails  user=userDetailsService.loadUserByUsername(String.valueOf(username));
        if (user !=null && password != null && passwordEncoder.matches(String.valueOf(password),user.getPassword())){
            userPassAuthToken=new UserPassAuthToken(String.valueOf(username),user.getPassword(),user.getAuthorities());
            userPassAuthToken.setUsersDetails(user);
        }else {
            userPassAuthToken = new UserPassAuthToken(String.valueOf(username), password);
            userPassAuthToken.setAuthenticated(false);
        }
        return userPassAuthToken;
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UserPassAuthToken.class.equals(authType);
    }
}
