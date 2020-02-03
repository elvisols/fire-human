package com.fire.human.security;

import com.fire.human.model.Person;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fire.human.security.SecurityConstant.EXPIRATION_TIME;
import static com.fire.human.security.SecurityConstant.SECRET;


@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication) {
        Person person = (Person) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);

        System.out.println("Token to expire: " + expiryDate);

        String userId = Long.toString(person.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", Long.toString(person.getId()));
        claims.put("username", person.getUsername());
        claims.put("firstname", person.getFirst_name());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch(SignatureException se) {
            System.out.println("..Invalid JWT Signature");
        } catch(MalformedJwtException me) {
            System.out.println("..Invalid JWT Token");
        } catch(ExpiredJwtException ee) {
            System.out.println("..Expired JWT token");
        } catch (UnsupportedJwtException ue) {
            System.out.println("..Unsupported JWT token");
        } catch (IllegalArgumentException ie) {
            System.out.println("..JWT claims string is empty");
        }
        return false;
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");

        return Long.parseLong(id);
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("username", String.class);
    }

    public static String createToken(String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return jwt;
    }
}
