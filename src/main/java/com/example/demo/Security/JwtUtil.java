package com.example.demo.Security;
import com.example.demo.model.Admin;
import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import com.example.demo.model.Users;

@Component

public class JwtUtil {

    private String secret = "yoursecretkey/Admin/123/GRACIAOLLO/16/05/2007"; // Should be stored securely, e.g., in environment variables

    // Generates a JWT token based on user information
    public String generateToken(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getUsername())
                .claim("role", admin.getRole()) // Custom claims for role
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiration
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

//     Extracts claims (e.g., username, role) from the JWT token
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

//     Validates the JWT token by checking username and expiration
    public boolean validateToken(String token, Admin admin) {
        final String username = extractClaims(token).getSubject();
        return (username.equals(admin.getUsername()) && !isTokenExpired(token));
    }

//     Checks if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
