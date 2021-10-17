package com.app.datingapp.security;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    public String getEmailFromToken(String token) {
        var signingKey = SecurityConstants.JWT_SECRET.getBytes();

        var parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace("Bearer ", ""));

        return parsedToken
                .getBody()
                .getSubject();
    }
}
