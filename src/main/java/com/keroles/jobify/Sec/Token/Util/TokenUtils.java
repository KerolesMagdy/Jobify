package com.keroles.jobify.Sec.Token.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.keroles.jobify.Model.Custom.UsersDetails;
import com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil;
import com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil.UserType;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Sec.Token.Model.TokenModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@Getter
public class TokenUtils {
    private final long ACCESS_TOKEN_VALIDITY=432000L;//7days
    private final long REFRESH_TOKEN_VALIDITY=604800L;//7days
    private static final String TOKEN_SECRET="jobify-app";
    private  final Algorithm algorithm=Algorithm.HMAC512("Keroles");
//    @Getter(AccessLevel.NONE)
//    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public char[] generateToken(char[] username, List<char[]>authority, UserType type,long validityTime){
//     return Jwts
//            .builder()
//            .setClaims(prepareClaims(authentication))
//            .setSubject(authentication.getName())
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + validityTime * 1000))
//            .signWith(key)
//            .compact();
        Map<String,Object> claims=prepareClaims(authority);
        return JWT
                .create()
                .withSubject(String.valueOf(username))
                .withExpiresAt(new Date(System.currentTimeMillis()+validityTime*1000))
                .withClaim("roles", (List<String>) claims.get("roles"))
                .withClaim("type", type.toString().replace("",""))
                .sign(algorithm).toCharArray();
    }
    public CompositeToken generateCompositeToken(char[] username, List<char[]>authority, UserType type){
        CompositeToken compositeToken= CompositeToken.builder().build();
        compositeToken.setAccessToken(generateToken(username,authority,type,ACCESS_TOKEN_VALIDITY));
        compositeToken.setRefreshToken(generateToken(username,authority,type,REFRESH_TOKEN_VALIDITY));
        return compositeToken;
    }


    public TokenModel getTokenModel(char[] token){
//        Claims claims= getAllClaimsFromToken(token);
//        return TokenModel
//                .builder()
//                .username(claims.getSubject())
//                .roles((List<String>)claims.get("roles"))
//                .createdAt(new Date( (Long) claims.get("created")))
//                .expirationDate(claims.getExpiration())
//                .build();
        DecodedJWT decodedJWT=JWT.decode(String.valueOf(token));
        List<char[]> roles=new ArrayList();
        decodedJWT.getClaim("roles").asList(String.class).forEach(s -> roles.add(s.toCharArray()));
        return TokenModel
                .builder()
                .username(decodedJWT.getSubject().toCharArray())
                .usertype(String.valueOf(decodedJWT.getClaim("type")).replace("\"",""))
                .roles(roles)
                .createdAt(decodedJWT.getIssuedAt())
                .expirationDate(decodedJWT.getExpiresAt())
                .build();
    }

    public boolean validateToken(TokenModel tokenModel, UserDetails usersDetails){
        return (validateTokenNameIdentity(tokenModel,usersDetails)
                && ! isTokenDateExpired(tokenModel.getExpirationDate()));
    }

    public boolean validateTokenNameIdentity(TokenModel tokenModel, UserDetails usersDetails){
        return (usersDetails!=null && Arrays.equals(tokenModel.getUsername(),usersDetails.getUsername().toCharArray()));
    }

    private boolean isTokenDateExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
    private Map<String,Object> prepareClaims( List<char[]>authority){
        List<String> list=new ArrayList<>();
        authority.forEach(chars -> list.add(String.valueOf(chars)));
        Map<String, Object> claims = new HashMap<>();
        claims.put("created",new Date());
        claims.put("roles",list);
        return claims;
    }
}
