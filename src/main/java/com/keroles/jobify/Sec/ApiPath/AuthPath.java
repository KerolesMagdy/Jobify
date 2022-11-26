package com.keroles.jobify.Sec.ApiPath;

import io.jsonwebtoken.lang.Collections;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
            "/token/refresh",
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
            "/u/v/"
    );

    private List<String> userPasswordFilter_pathShouldDo
    = Arrays.asList(
        "/user/login",
            "/user/cred/d"
    );

    private List<String> tokenFilter_pathShouldNotDo
    = Arrays.asList(
            "/user/register",
            "/user/reset-pass",
            "/otp/reset",
            "/u/v/"
    );

    public List<String> getTokenShouldNotDo(){
        List<String> list=new ArrayList<>();
        userPasswordFilter_pathShouldDo.forEach(s -> list.add(s));
        tokenFilter_pathShouldNotDo.forEach(s -> list.add(s));
        return list;
    }
}
