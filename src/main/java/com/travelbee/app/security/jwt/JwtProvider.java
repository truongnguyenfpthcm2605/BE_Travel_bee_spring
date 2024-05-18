package com.travelbee.app.security.jwt;

import com.travelbee.app.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${security.secert}")
    private  String secert;
    @Value("${security.time}")
    private Integer time;

    public String createToken(UserPrinciple userPrinciple){
        return Jwts.builder()
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ time))
                .signWith(SignatureAlgorithm.HS256, secert).compact();

    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secert).parseClaimsJws(token.trim());
            return true;
        }catch (Exception e) {
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secert).parseClaimsJws(token).getBody().getSubject();
    }
}
