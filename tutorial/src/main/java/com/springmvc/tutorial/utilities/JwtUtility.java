//package com.springmvc.tutorial.utilities;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtUtility {
//    @Value("${Jwt.Secret}")
//    private static String SECRET;
//
//    @Value("${jwt.cookieExpiry}")
//    private int cookieExpiry;
//
//    public String extractUsername(String token) {
//        return this.extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return this.extractClaim(token, Claims::getExpiration);
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = this.extractUsername(token);
//        return username.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
//    }
//
//    public String GenerateToken(String email) {
//        Map<String, Object> claims = new HashMap<>();
//        return this.createToken(claims, email);
//    }
//
//    public String GenerateToken(UserDetails userDetails) {
//        return Jwts.builder().setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSingKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = this.extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(this.getSingKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return this.extractExpiration(token).before(new Date());
//    }
//
//    private String createToken(Map<String, Object> claims, String username) {
//        Date now = new Date();
//        Date expirityDate = new Date(now.getTime() + cookieExpiry * 1000L);
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(expirityDate)
//                .signWith(this.getSingKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key getSingKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String email = extractUsername(token);
//        return email.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
//    }
//
//}
