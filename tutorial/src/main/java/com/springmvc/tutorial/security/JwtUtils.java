//package com.springmvc.tutorial.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private final long expirationMs = 3600000; // 1 giờ
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                .signWith(key)
//                .compact();
//    }
//
//    public String validateToken(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//        } catch (JwtException | IllegalArgumentException e) {
//            throw new RuntimeException("Invalid JWT Token");
//        }
//    }
//}
