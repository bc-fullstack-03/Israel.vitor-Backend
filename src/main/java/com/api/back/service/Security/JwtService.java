package com.api.back.service.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
@Service
public class JwtService implements IJwtService{

    private final long EXPIRATION_TIME = 7200000;


    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, UUID userId) {
        var claims = Jwts.parserBuilder().setSigningKey(genSignInKey()).build().parseClaimsJws(token).getBody();

        var sub = claims.getSubject();
        var tExpiration = claims.getExpiration();

        return (sub.equals(userId.toString()) && tExpiration.before(new Date()));
    }

    private Key genSignInKey() {

        var bytes  = Decoders.BASE64.decode("BHD73F29356NF47GJ47UF7843FE85FI78378FJ3\n");

        return Keys.hmacShaKeyFor(bytes);
    }
}
