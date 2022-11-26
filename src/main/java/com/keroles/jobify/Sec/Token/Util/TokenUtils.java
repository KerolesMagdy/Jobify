package com.keroles.jobify.Sec.Token.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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


    public String generateToken(String username, List<String>authority,long validityTime){
//     return Jwts
//            .builder()
//            .setClaims(prepareClaims(authentication))
//            .setSubject(authentication.getName())
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + validityTime * 1000))
//            .signWith(key)
//            .compact();

        return JWT
                .create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_VALIDITY*1000))
                .withClaim("roles",(List<String>)prepareClaims(authority).get("roles"))
                .sign(algorithm);
    }
    public CompositeToken generateCompositeToken(String username, List<String>authority, long validityTime){
        CompositeToken compositeToken= CompositeToken.builder().build();
        compositeToken.setAccessToken(JWT
                .create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_VALIDITY*1000))
                .withClaim("roles",(List<String>)prepareClaims(authority).get("roles"))
                .sign(algorithm));
        compositeToken.setRefreshToken(JWT
                .create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()+REFRESH_TOKEN_VALIDITY*1000))
                .withClaim("roles",(List<String>)prepareClaims(authority).get("roles"))
                .sign(algorithm));
        return compositeToken;
    }


    public TokenModel getTokenModel(String token){
//        Claims claims= getAllClaimsFromToken(token);
//        return TokenModel
//                .builder()
//                .username(claims.getSubject())
//                .roles((List<String>)claims.get("roles"))
//                .createdAt(new Date( (Long) claims.get("created")))
//                .expirationDate(claims.getExpiration())
//                .build();
        DecodedJWT decodedJWT=JWT.decode(token);

        return TokenModel
                .builder()
                .username(decodedJWT.getSubject())
                .roles(decodedJWT.getClaim("roles").asList(String.class))
                .createdAt(decodedJWT.getIssuedAt())
                .expirationDate(decodedJWT.getExpiresAt())
                .build();
    }

    public boolean validateToken(TokenModel tokenModel, UserDetails userDetails){

        return (userDetails!=null
                && tokenModel.getUsername().equals(userDetails.getUsername())
                && ! isTokenExpired(tokenModel.getExpirationDate()));
    }

    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
    private Map<String,Object> prepareClaims( List<String>authority){
        Map<String, Object> claims = new HashMap<>();
        claims.put("created",new Date());
        claims.put("roles",authority);
        return claims;
    }


}
