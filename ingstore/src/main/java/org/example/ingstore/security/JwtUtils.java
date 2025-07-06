package org.example.ingstore.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.example.ingstore.utils.AppConstants.*;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value(JWT_SECRET_KEY)
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error(ERROR_INVALID_JWT, e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(ERROR_EXPIRED_JWT, e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(ERROR_UNSUPPORTED_JWT, e.getMessage());
        } catch (SignatureException e) {
            logger.error(ERROR_INVALID_SIGNATURE, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(ERROR_EMPTY_CLAIMS, e.getMessage());
        }
        return false;
    }
}
