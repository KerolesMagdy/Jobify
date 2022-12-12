package com.keroles.jobify.Sec.ApiPath;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class AuthPath {

    private List<String> allAuthPath
            = Arrays.asList(
                    "/user",
            "/user/login",
            "/user/role",
            "/user/update-name",
            "/otp/",
            "/token/user/refresh",
            "/user/cred/d",
            "/user/d/",
            "/user/details",
            "/user-experience/"
    );
    private List<String> allNotAuthPath
                = Arrays.asList(
            "/user/register",
            "/user/reset-pass",
            "/otp/reset",
            "/u/v/",
            "/e/v/"
    );

    private List<String> userPasswordFilter_pathShouldDo
    = Arrays.asList(
        "/user/login",
            "/user/cred/d"
    );

    private List<String> employerAuthFilter_pathShouldDo
    = Arrays.asList(
        "/employer/login"
    );

    private List<String> employerTokenAuthFilter_pathShouldDo
            = Arrays.asList(
            "/token/emp/refresh"
    );

    private List<String> public_path
    = Arrays.asList(
            "/user/register",
            "/user/reset-pass",
            "/otp/reset",
            "/u/v/",
            "/user/image/",
            "/user/cv/"
    );

    public List<String> getUserTokenShouldNotDo(){
        List<String> list=new ArrayList<>();
        list.addAll(userPasswordFilter_pathShouldDo);
        list.addAll(employerAuthFilter_pathShouldDo);
        list.addAll(employerTokenAuthFilter_pathShouldDo);
        list.addAll(public_path);
//        userPasswordFilter_pathShouldDo.forEach(s -> list.add(s));
//        employerAuthFilter_pathShouldDo.forEach(s -> list.add(s));
//        employerTokenAuthFilter_pathShouldDo.forEach(s -> list.add(s));
//        public_path.forEach(s -> list.add(s));
        return list;
    }
}
