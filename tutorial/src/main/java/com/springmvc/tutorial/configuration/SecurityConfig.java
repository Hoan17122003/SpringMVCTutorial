//package com.springmvc.tutorial.configuration;
//
//import com.springmvc.tutorial.filter.JwtAuthenticationFilter;
//import com.springmvc.tutorial.service.CustomDetaillService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    // private final PublicEndPointMatcher publicEndPointMatcher;
//    private final JwtAuthenticationFilter jwtFiltetAuthentication;
//
//    private final CustomDetaillService customDetaillService;
//
//    @Autowired
//    public SecurityConfig(
//            // PublicEndPointMatcher publicEndPointMatcher,
//            JwtAuthenticationFilter jwtFiltetAuthentication,
//            CustomDetaillService customDetaillService) {
//        // this.publicEndPointMatcher = publicEndPointMatcher;
//        this.jwtFiltetAuthentication = jwtFiltetAuthentication;
//        this.customDetaillService = customDetaillService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/login")
//                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.GET, "Category/login", "/error").permitAll() // Cho phép
//                                                                                                 // truy cập
//                                                                                                 // /login và
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "Category/login", "error").permitAll()
//                        // /error không cần xác thực
//                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
//                                "/images/**")
//                        .permitAll() // Cho phép truy cập tài nguyên tĩnh
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .loginPage("/login")// sai duong dan login
//                        .permitAll()
//                        .defaultSuccessUrl("/", true)
//                        .failureUrl("/login?error=true")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll())
//                // .accessDeniedPage("/access-denied")
//                .sessionManagement(session -> session
//                        .sessionFixation().migrateSession()
//                        .maximumSessions(1)
//                        .expiredUrl("/login?expired=true")
//                        .sessionRegistry(sessionRegistry()))
//                .authenticationProvider(this.daoAuthenticationProvider())
//                .addFilterBefore(new JwtAuthenticationFilter(new CustomDetaillService()),
//                        UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(customDetaillService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//}
