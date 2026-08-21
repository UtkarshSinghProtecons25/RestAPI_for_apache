package br.com.example.davidarchanjo.security;

import br.com.example.davidarchanjo.model.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key key = Keys.hmacShaKeyFor(
            "change-this-demo-secret-key-to-at-least-32-bytes!!"
                    .getBytes(StandardCharsets.UTF_8)
    );

    public String generate(User user) {

        Date now = new Date();

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(
                        new Date(now.getTime() + 3600000)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims claims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validate(String token) {

        try {
            claims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}