package dev.lxqtpr.linda.lindaforumengine.authentication;

import dev.lxqtpr.linda.lindaforumengine.core.properties.JwtProperties;
import dev.lxqtpr.linda.lindaforumengine.core.exceptions.JwtException;
import dev.lxqtpr.linda.lindaforumengine.domain.user.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Component
public class JwtService {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;
    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getAccessSecret()));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getRefreshSecret()));
    }

    public String generateAccessToken(UserEntity user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant =
                now.plusMinutes(jwtProperties.getAccessExpirationInMinutes())
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("roles", user.getRole())
                .compact();
    }

    public String generateRefreshToken(UserEntity user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant =
                now.plusMinutes(jwtProperties.getRefreshExpirationInMinutes())
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(String token,SecretKey secret) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            throw new JwtException(e.getMessage());
        }
    }

    public String getUsernameFromAccessClaims(String token) {
        return getClaims(token, jwtAccessSecret).getSubject();
    }

    public String getUsernameFromRefreshClaims(String token) {
        return getClaims(token, jwtRefreshSecret).getSubject();
    }

    private Claims getClaims(String token,SecretKey secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
