package com.keroles.jobify.Sec.Provider;

import com.keroles.jobify.Model.Custom.EmployerUserDetails;
import com.keroles.jobify.Model.Custom.UsersDetails;
import com.keroles.jobify.Sec.Authentication.EmployerPassAuthToken;
import com.keroles.jobify.Service.Implementation.EmployerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployerAuthProvider implements AuthenticationProvider {

    @Autowired
    EmployerService employerService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public EmployerPassAuthToken authenticate(Authentication authentication) throws AuthenticationException {
log.error("1111111111111111111111111111111");
        char[]username =authentication.getName().toCharArray();
        char[] password=authentication.getCredentials().toString().toCharArray();
        log.error("222222222222222222222222222");
        EmployerPassAuthToken employerPassAuthToken;
        EmployerUserDetails employer=employerService.loadUserByUsername(String.valueOf(username));
        log.error("3333333333333333333333333333333");
        if (employer !=null && password != null && passwordEncoder.matches((String.valueOf(password)),employer.getPassword())){
            log.error("44444444444444444444444444");
            employerPassAuthToken=new EmployerPassAuthToken(String.valueOf(username),employer.getPassword(),employer.getAuthorities());
            employerPassAuthToken.setEmployerUserDetails(employer);
        }else {
            log.error("55555555555555555555555");
            employerPassAuthToken = new EmployerPassAuthToken(String.valueOf(username), password);
            employerPassAuthToken.setAuthenticated(false);
        }
        log.error("6666666666666666666666666666666666");
        return employerPassAuthToken;
    }

    @Override
    public boolean supports(Class<?> authType) {
        log.error("6666666666666666666666666666666666");

        return EmployerPassAuthToken.class.equals(authType);
    }
}
