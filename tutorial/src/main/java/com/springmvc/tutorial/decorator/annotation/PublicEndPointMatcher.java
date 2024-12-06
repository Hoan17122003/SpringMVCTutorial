//package com.springmvc.tutorial.annotation;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerMapping;
//
//@Component
//public class PublicEndPointMatcher implements RequestMatcher {
//
//    @Override
//    public boolean matches(HttpServletRequest request) {
//        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            if (handlerMethod.getMethodAnnotation(Public.class) != null
//                    || handlerMethod.getBeanType().isAnnotationPresent(Public.class)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
