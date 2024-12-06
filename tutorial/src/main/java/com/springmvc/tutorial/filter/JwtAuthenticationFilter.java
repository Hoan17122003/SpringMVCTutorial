//package com.springmvc.tutorial.filter;
//
//import com.springmvc.tutorial.service.CustomDetaillService;
//import com.springmvc.tutorial.utilities.JwtUtility;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.thymeleaf.util.StringUtils;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//@Service
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtility jwtUtility;
//    private final CustomDetaillService customDetaillService;
//
//    public JwtAuthenticationFilter(CustomDetaillService customDetaillService) {
//        this.customDetaillService = customDetaillService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String jwtSession = request.getHeader("cookie");
//        final String jwt;
//        final String userEmail;
//        if (StringUtils.isEmpty(jwtSession) || !StringUtils.startsWith(jwtSession, "Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = jwtSession.substring(7);
//        userEmail = this.jwtUtility.extractUsername(jwt);
//        if (StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = customDetaillService.loadUserByUsername(userEmail);
//            if (jwtUtility.isTokenValid(jwt, userDetails)) {
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                securityContext.setAuthentication(token);
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
