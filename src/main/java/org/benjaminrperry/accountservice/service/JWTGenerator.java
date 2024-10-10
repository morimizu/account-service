package org.benjaminrperry.accountservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.benjaminrperry.accountservice.db.model.Account;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;

@Component
public class JWTGenerator {
    private SecretKey key = Jwts.SIG.HS256.key().build();

    private JwtParser parser = Jwts.parser().verifyWith(key).build();

    private String issuer = "com.benjaminrperry.accountservice";

    public String generateToken(Account account) {
        return Jwts.builder()
                .issuer(issuer)
                .subject(account.getId().toString())
                .issuedAt(Date.from(Instant.now()))
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }
}
