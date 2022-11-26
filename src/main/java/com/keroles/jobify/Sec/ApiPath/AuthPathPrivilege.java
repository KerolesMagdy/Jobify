package com.keroles.jobify.Sec.ApiPath;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;


@Component
@Data
public class AuthPathPrivilege {

     private List<String> super_adminPrivilegePath
     = Arrays.asList(
             "/user",
             "/user/login",
             "/user/role",
             "/user/update-name/",
             "/otp",
             "/user/d/",
             "/user/details"
     );

     private List<String> adminPrivilegePath
             = Arrays.asList(
                     "/user",
             "/user/login",
             "/user/update-name/",
             "/otp",
             "/user/d/",
             "/user/details"
     );

     private List<String> managerPrivilegePath
             = Arrays.asList(
                     "/user/login",
             "/user/update-name/",
             "/otp",
             "/user/cred/d/",
             "/user/details"
     );

     private List<String> userPrivilegePath
             = Arrays.asList(
                     "/user/login",
             "/user/update-name/",
             "/user/cred/d/",
             "/user/details"
     );



}
