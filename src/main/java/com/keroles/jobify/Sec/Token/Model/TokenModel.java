package com.keroles.jobify.Sec.Token.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {

    private String username;
    private List<String> roles=new ArrayList<>();
    private Date createdAt;
    private Date expirationDate;

    public Collection<SimpleGrantedAuthority> getAuthority(){
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        if (roles!=null)
            for (String userRole : roles) authorities.add(new SimpleGrantedAuthority(userRole));
        return authorities;
    }


}
