package com.keroles.jobify.Sec.AuthFilter;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.Custom.EmployerUserDetails;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.Authentication.EmployerPassAuthToken;
import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import com.keroles.jobify.Sec.Exception.Handler.AuthFailureHandler;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Sec.Token.Model.TokenModel;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import com.keroles.jobify.Service.Implementation.EmployerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil.UserType.EMPLOYER;

@Component
@Slf4j
public class TokenEmployerAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthPath authPath;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private Environment environment;
    @Autowired
    private EmployerService employerService;
    @Autowired
    AuthFilterUtil authFilterUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        char[] authorization=request.getHeader("Authorization").toCharArray();
        if(authorization!=null&&String.valueOf(authorization).startsWith("Bearer ")){
            char[] token = String.valueOf(authorization).substring("Bearer ".length()).toCharArray();
            try {
                TokenModel tokenModel = tokenUtils.getTokenModel(token);
                EmployerUserDetails employerUserDetails = employerService.loadUserByUsername(String.valueOf(tokenModel.getUsername()));
                if (employerUserDetails==null || (tokenModel!=null && !tokenModel.getUsertype().equals(EMPLOYER.toString()))){
                authFilterUtil.onFailureAuthInvalidToken(
                        new GlobalObjectNotFoundException(environment.getProperty("validate.message.employer.not_found"))
                        ,response
                        ,request
                        ,null);
                }

                if (employerUserDetails!=null && !employerUserDetails.isEnabled()) {
                    authFilterUtil.onAccountNotVerified(response, request);
                    return;
                }
                if (tokenModel!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                    if (!tokenUtils.validateToken(tokenModel,employerUserDetails)) {
                        authFilterUtil.onFailureAuthExpiredToken(response, request);
                        return;
                    } else {
                        EmployerPassAuthToken  employerPassAuthToken = new EmployerPassAuthToken(tokenModel.getUsername(),
                                null,
                                tokenModel.getAuthority());
                        employerPassAuthToken.getAuthorities().forEach(grantedAuthority -> log.error("222  "+grantedAuthority.getAuthority()));
                        SecurityContextHolder.getContext().setAuthentication(employerPassAuthToken);

                        if(request.getServletPath().equals("/token/emp/refresh")){
                            List<char[]> authors=new ArrayList<>();
                            employerPassAuthToken.getAuthorities().forEach(grantedAuthority -> authors.add(grantedAuthority.getAuthority().toCharArray()));
                            CompositeToken compositeToken= tokenUtils.generateCompositeToken(employerPassAuthToken.getName().toCharArray(), authors, EMPLOYER);
                            authFilterUtil.onSuccessfulAuth(response, compositeToken);
                        }
                        filterChain.doFilter(request, response);
                    }
                }
            }catch (Exception ex){
                log.error("error {}",ex.getMessage());
                authFilterUtil.onFailureAuthInvalidToken(ex,response,request,null);
            }
        }else {authFilterUtil.onFailureAuthInvalidToken(null,response,request,null);}
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        log.error("ServletPath  {}",request.getServletPath());
        for (String s : authPath.getEmployerTokenAuthFilter_pathShouldDo())
            if (request.getServletPath().startsWith(s)) return false;
        return true;
    }

}
