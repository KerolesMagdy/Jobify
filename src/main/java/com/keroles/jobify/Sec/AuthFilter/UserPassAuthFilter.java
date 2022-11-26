package com.keroles.jobify.Sec.AuthFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.Mapper.UserMapper;
import com.keroles.jobify.Sec.Exception.Handler.AuthFailureHandler;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import com.keroles.jobify.Service.Implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserPassAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private AuthenticationManager authManager;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthPath authPath;
    @Autowired
    private UserService userService;
    @Autowired
    AuthFailureHandler authFailureHandler;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username=request.getHeader("username");
        String password=request.getHeader("password");

        UserPassAuthToken authentication=
                new UserPassAuthToken(username,password);
        UserPassAuthToken currentAuth =(UserPassAuthToken)authManager.authenticate(authentication);
        if (!currentAuth.isAuthenticated()){
            onFailureAuth(response,request,null);
        }

        else {
            SecurityContextHolder.getContext().setAuthentication(currentAuth);
            if(request.getServletPath().equals(authPath.getUserPasswordFilter_pathShouldDo().get(0))) {
                UserDto responseBody = userMapper.mapUserToDto(currentAuth.getUsersDetails().getUsers());
                CompositeToken compositeToken = tokenUtils.generateCompositeToken(currentAuth.getName(),
                        currentAuth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                        , tokenUtils.getACCESS_TOKEN_VALIDITY());
                responseBody.setToken(compositeToken);
                onSuccessfulAuth(response, responseBody);
            }
            filterChain.doFilter(request,response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !authPath.getUserPasswordFilter_pathShouldDo().contains(request.getServletPath());
    }

    private void onSuccessfulAuth(HttpServletResponse response,Object responseBody) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

    private void onFailureAuth(HttpServletResponse response,HttpServletRequest request,String error_message) throws IOException {
        authFailureHandler.handleUserAuthFailedException(response,request,error_message);
    }

}
