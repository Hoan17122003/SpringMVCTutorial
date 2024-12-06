package com.springmvc.tutorial.controller;

import com.springmvc.tutorial.dto.request.AuthDTO;
import com.springmvc.tutorial.model.entities.Category;
import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Supplier;
//import com.springmvc.tutorial.utilities.JwtUtility;

import io.lettuce.core.dynamic.annotation.Value;
import jakarta.servlet.http.HttpServletResponse;

import java.net.http.HttpHeaders;

import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

//    private final AuthenticationManager authenticationManager;

//    public final JwtUtility jwtUtility;

    // @Value("${jwt.cookieExpiry}")
    private final Integer cookieExpriry = 1800;

//    public HomeController(AuthenticationManager authenticationManager, JwtUtility jwtUtility) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtility = jwtUtility;
//    }

    // @GetMapping(path = "/login")
    // public String Login(ModelMap modelMap) {
    // return "auth/login";
    // }

    @GetMapping(path = "/")
    public String Index(ModelMap modelMap) {
        return "Home/Index";
    }

    // @PostMapping("/Login")
    // public ModelAndView HandleLoginPost(@RequestBody AuthDTO authDTO,
    // HttpServletResponse response) {
    // Authentication authentication = this.authenticationManager
    // .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(),
    // authDTO.getPassword()));
    // if (authentication.isAuthenticated()) {
    // String jwtToken = this.jwtUtility.GenerateToken(authDTO.getEmail());
    // ResponseCookie cookie = ResponseCookie.from("acccessToken", jwtToken)
    // .httpOnly(true).secure(true)
    // .path("/")
    // .maxAge(this.cookieExpriry)
    // .build();
    // response.addHeader(org.springframework.http.HttpHeaders.SET_COOKIE,
    // jwtToken);
    // }
    // return new ModelAndView("redirect:/");
    // }
}
