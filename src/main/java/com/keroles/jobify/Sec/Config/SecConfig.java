package com.keroles.jobify.Sec.Config;

//import com.keroles.jobify.Sec.AuthFilter.EmployerAuthFilter;
import com.keroles.jobify.Sec.AuthFilter.EmployerAuthFilter;
import com.keroles.jobify.Sec.AuthFilter.TokenEmployerAuthFilter;
import com.keroles.jobify.Sec.AuthFilter.TokenUserAuthFilter;
import com.keroles.jobify.Sec.AuthFilter.UserPassAuthFilter;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.ApiPath.AuthPathPrivilege;
//import com.keroles.jobify.Sec.Provider.EmployerAuthProvider;
import com.keroles.jobify.Sec.Provider.EmployerAuthProvider;
import com.keroles.jobify.Sec.Provider.UserPassAuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecConfig {

    @Autowired
    UserPassAuthProvider userPassAuthProvider;
    @Autowired
    EmployerAuthProvider employerAuthProvider;
    @Autowired
    UserPassAuthFilter userPassAuthFilter;
    @Autowired
    EmployerAuthFilter employerAuthFilter;
    @Autowired
    TokenUserAuthFilter tokenUserAuthFilter;
    @Autowired
    TokenEmployerAuthFilter tokenEmployerAuthFilter;
    @Autowired
    AuthPathPrivilege userPrivilege;
    @Autowired
    AuthPath authPath;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(authPath.getAllNotAuthPath().iterator().next()).permitAll()
                .antMatchers(authPath.getAllAuthPath().iterator().next()).authenticated()
                .antMatchers(userPrivilege.getSuper_adminPrivilegePath().iterator().next()).hasAnyAuthority("SUPER_ADMIN")
                .antMatchers(userPrivilege.getAdminPrivilegePath().iterator().next()).hasAnyAuthority("ADMIN")
                .antMatchers(userPrivilege.getManagerPrivilegePath().iterator().next()).hasAnyAuthority("MANAGER")
                .antMatchers(userPrivilege.getUserPrivilegePath().iterator().next()).hasAnyAuthority("USER")
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(STATELESS)
                .and().authenticationProvider(userPassAuthProvider)
                .authenticationProvider(employerAuthProvider)
                .addFilterAt(userPassAuthFilter , BasicAuthenticationFilter.class)
                .addFilterAfter(employerAuthFilter , UserPassAuthFilter.class)
                .addFilterAfter(tokenUserAuthFilter , EmployerAuthFilter.class)
                .addFilterAfter(tokenEmployerAuthFilter , TokenUserAuthFilter.class)
        ;
//        http.formLogin().failureHandler(customAuthenticationFailureHandler)
//                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> myAuthenticationProviders) {
        return new ProviderManager(myAuthenticationProviders);
    }

}
