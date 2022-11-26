package com.keroles.jobify.Sec.Config;

import com.keroles.jobify.Sec.AuthFilter.TokenAuthFilter;
import com.keroles.jobify.Sec.AuthFilter.UserPassAuthFilter;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.ApiPath.AuthPathPrivilege;
import com.keroles.jobify.Sec.Provider.UserPassAuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecConfig {

    @Autowired
    UserPassAuthProvider userPassAuthProvider;
    @Autowired
    UserPassAuthFilter userPassAuthFilter;
    @Autowired
    TokenAuthFilter tokenAuthFilter;
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
                .addFilterAt(userPassAuthFilter , BasicAuthenticationFilter.class)
                .addFilterAfter(tokenAuthFilter , BasicAuthenticationFilter.class)
        ;
//        http.formLogin().failureHandler(customAuthenticationFailureHandler)
//                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }

//    @Bean
//    public CustomUsernamePasswordAuthFilter getCustomUsernamePasswordAuthFilter(){
//        return new CustomUsernamePasswordAuthFilter();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
