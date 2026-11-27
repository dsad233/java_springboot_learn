package com.example.common.modules;

import com.example.common.libs.enums.Type;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    @Value("${spring.jwt.access.secret}")
    private String secretKey;

    @Value("${spring.jwt.access.expiration}")
    private Long accessExpire;

    @Value("${spring.jwt.refresh.secret}")
    private String refreshSecretKey;

    @Value("${spring.jwt.refresh.expiration}")
    private Long refreshExpire;

    // SecretKey 객체 생성 - HMAC-SHA 알고리즘에 적합한 키 생성
    private SecretKey getSigningKey(String tokenType) {
        if(Type.TokenType.REFRESH.getProp().equals(tokenType)) {
            byte[] keyBytes = Decoders.BASE64.decode(refreshSecretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String sign(String payload, String tokenType) {
        if(Type.TokenType.REFRESH.getProp().equals(tokenType)) {
            return Jwts.builder().subject(payload).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + refreshExpire)).signWith(getSigningKey(tokenType)).compact();
        }

        return Jwts.builder().subject(payload).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + accessExpire)).signWith(getSigningKey(tokenType)).compact();
    }

    public Claims verify(String token, String tokenType) {
        return Jwts.parser().verifyWith(getSigningKey(tokenType)).build().parseSignedClaims(token).getPayload();
    }
}
