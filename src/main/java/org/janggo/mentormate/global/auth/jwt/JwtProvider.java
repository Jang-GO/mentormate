package org.janggo.mentormate.global.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private static final String JWT_SECRET = "jwtSecret".repeat(10);
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private final SecretKey key;

    public JwtProvider() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }

    public String createToken(final String token){
        final Date now = new Date();

        final Claims claims = Jwts.claims()
                .issuedAt(now)
                .subject(token)
                .expiration(new Date(now.getTime() + EXPIRATION_TIME))
                .build();

        return Jwts.builder()
                .claims(claims)
                .signWith(key)
                .compact();
    }
}
