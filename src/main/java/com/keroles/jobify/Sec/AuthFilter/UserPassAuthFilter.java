package com.keroles.jobify.Sec.AuthFilter;

import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.Mapper.UserMapper;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil.UserType.USER;

@Component
@Slf4j
public class UserPassAuthFilter extends OncePerRequestFilter {

    @Autowired
//    @Lazy
    private AuthenticationManager authManager;
    @Autowired
    private AuthPath authPath;
    @Autowired
    AuthFilterUtil authFilterUtil;
    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        char[] username=request.getHeader("username").toCharArray();
        char[] password=request.getHeader("password").toCharArray();

        UserPassAuthToken authentication= new UserPassAuthToken(String.valueOf(username),String.valueOf(password));
        UserPassAuthToken currentAuth =(UserPassAuthToken)authManager.authenticate(authentication);

        if (!currentAuth.isAuthenticated()){
            authFilterUtil.onFailureAuth(response,request,null);
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(currentAuth);
            if(request.getServletPath().equals(authPath.getUserPasswordFilter_pathShouldDo().get(0))) {
                UserDto responseBody = userMapper.mapUserToDto(currentAuth.getUsersDetails().getUsers());
                responseBody.setToken(authFilterUtil.prepareTokenToAuthResponse(
                        currentAuth.getAuthorities(),
                        currentAuth.getName().toCharArray(),
                        USER
                ));
                authFilterUtil.onSuccessfulAuth(response, responseBody);
            }
            filterChain.doFilter(request,response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.error("ServletPath  {}",request.getServletPath());
        return !authPath.getUserPasswordFilter_pathShouldDo().contains(request.getServletPath());
    }

}
