//package com.springmvc.tutorial.filter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Optional;
//
//import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class CustomAuthFilter extends OncePerRequestFilter {
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
//            throws IOException, ServletException {
//        Optional<String> tokenOptional = Optional.empty();
//        if (request.getCookies() != null) {
//            tokenOptional = Arrays.stream(request.getCookies())
//                    .filter(cookie -> cookie.getName().equals("accessToken"))
//                    .findFirst()
//                    .map(Cookie::getValue);
//
//        }
//        if (tokenOptional.isEmpty()) {
//            filter.doFilter(request, response);
//            return;
//        }
//        filter.doFilter(request, response);
//    }
//
//    private String getSessionIdFromCookies(HttpServletRequest request) {
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                if ("JSESSIONID".equals(cookie.getName())) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return null;
//    }
//
////    private Authentication authenticateSession(String sessionId) {
////        // Xác thực sessionId, bạn có thể sử dụng một service để lấy người dùng từ
////        // sessionId
////        return new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>());
////    }
//}
