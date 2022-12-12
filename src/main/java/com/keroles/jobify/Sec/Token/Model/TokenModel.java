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

    private char[] username;
    private List<char[]> roles=new ArrayList<>();
    private Date createdAt;
    private Date expirationDate;
    private String usertype;

    public Collection<SimpleGrantedAuthority> getAuthority(){
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        if (roles!=null)
            for (char[] userRole : roles) authorities.add(new SimpleGrantedAuthority(String.valueOf(userRole)));
        return authorities;
    }


}
