package com.hybird.security.jwt;

import com.hybird.security.userpincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.hybird.utils.Constants;

import java.util.Date;

@Component
public class JwtProvider {
    //class nay co tac dung tao chuoi token
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret  = "hoangvietduc";
    private int jwtExpiration = 86400;

    //builder token
    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .claim(Constants.AUTHEN_INFO.AUTHORITIES_KEY,userPrinciple.getRoles())
                .claim(Constants.AUTHEN_INFO.FULL_NAME,userPrinciple.getFullName())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date()
                .getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token) {
        try {
           Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        }catch (SignatureException e){
            logger.error("valid JWT signature - Message:{}",e);
        }catch (MalformedJwtException e){
            logger.error("the token invalid format :{}",e);
        }catch (UnsupportedJwtException e){
            logger.error("unsupported jwt token - Message:{}",e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token - Message:{}",e);
        }catch (IllegalArgumentException e){
            logger.error("The JWT token empty - Message:{}",e);
        }
        return false;
    }
    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
