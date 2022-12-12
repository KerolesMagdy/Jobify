package com.keroles.jobify.Sec.AuthFilter;

import com.keroles.jobify.Model.DTO.EmployerDto;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.Mapper.EmployerMapper;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.Authentication.EmployerPassAuthToken;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil.UserType.EMPLOYER;

@Component
@Slf4j
public class EmployerAuthFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private AuthenticationManager authManager;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthPath authPath;
    @Autowired
    AuthFilterUtil authFilterUtil;
    @Autowired
    EmployerMapper employerMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username=request.getHeader("username");
        String password=request.getHeader("password");

        EmployerPassAuthToken authentication= new EmployerPassAuthToken(username,password);
        EmployerPassAuthToken currentAuth=
                (EmployerPassAuthToken) authManager.authenticate(authentication);
        if (!currentAuth.isAuthenticated()){
            authFilterUtil.onFailureAuth(response,request,null);
        }else {
            SecurityContextHolder.getContext().setAuthentication(currentAuth);
                EmployerDto responseBody = employerMapper.mapToEmployerDto(currentAuth.getEmployerUserDetails().getEmployer());
//                List<char[]> authors=new ArrayList<>();
//                currentAuth.getAuthorities().forEach(grantedAuthority -> authors.add(grantedAuthority.getAuthority().toCharArray()));
//                CompositeToken compositeToken = tokenUtils
//                        .generateCompositeToken(currentAuth.getName().toCharArray(), authors);
            responseBody.setToken(authFilterUtil.prepareTokenToAuthResponse(
                    currentAuth.getAuthorities(),
                    currentAuth.getName().toCharArray()
                    , EMPLOYER
            ));
                authFilterUtil.onSuccessfulAuth(response, responseBody);
            filterChain.doFilter(request,response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.error("ServletPath  {}",request.getServletPath());
        return !authPath.getEmployerAuthFilter_pathShouldDo().contains(request.getServletPath());

    }
}
