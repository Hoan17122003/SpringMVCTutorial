//package com.springmvc.tutorial.security;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerMapping;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class AuthenticationGuard extends JwtAuthenticationFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
//
//        // Kiểm tra nếu handler là một method và có annotation @Public
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            if (handlerMethod.getMethodAnnotation(Public.class) != null ||
//                    handlerMethod.getBeanType().isAnnotationPresent(Public.class)) {
//                chain.doFilter(request, response);
//                return; // Bỏ qua xác thực cho các route @Public
//            }
//        }
//
//        // Nếu không phải @Public, thực hiện xác thực JWT
//        super.doFilterInternal(request, response, chain);
//    }
//}
