package com.example.pdfgeneratorclient.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider implements TokenProvider {

    @Value("${security.jwt.secret}")
    private String JWT_SECRET;

    @Value("${security.jwt.expire-time}")
    private long TOKEN_EXPIRE_TIME;

    @Value("${security.jwt.header}")
    private String TOKEN_HEADER;

    @Override
    public String generate(Long id) {
        Instant expireDate = Instant.now().plusMillis(TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expireDate))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return Long.valueOf(Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }

    @Override
    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(TOKEN_HEADER);
    }
}
