package org.benjaminrperry.accountservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.benjaminrperry.accountservice.db.model.Account;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;

@Component
public class JWTGenerator {

    private String tokenSecret = "lsadjdkjadljlakdjaslkjkdsjdhfeinnclsdiwneicnsdksanmwkdsnaweha";
    private SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.tokenSecret));

    private JwtParser parser = Jwts.parser().verifyWith(key).build();

    private String issuer = "com.benjaminrperry.accountservice";

    public String generateToken(Account account) {
        return Jwts.builder()
                .issuer(issuer)
                .subject(account.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }
}
