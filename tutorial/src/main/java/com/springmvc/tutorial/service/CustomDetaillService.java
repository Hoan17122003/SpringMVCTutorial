//package com.springmvc.tutorial.service;
//
//import com.springmvc.tutorial.model.entities.Employee;
//import com.springmvc.tutorial.model.repository.employee.IEmployeeRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.stream.Collectors;
//
//@Service
//@NoArgsConstructor
//@AllArgsConstructor
//public class CustomDetaillService implements UserDetailsService {
//        @Autowired
//        private IEmployeeRepository employeeRepository;
//
//        @Override
//        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                Employee employee = employeeRepository.findByEmail(email);
//                if (employee == null)
//                        new UsernameNotFoundException("User not found: " + email);
//                String[] roles = employee.getRoleNames()
//                                .stream()
//                                .map(element -> element.name())
//                                .findFirst().orElse(null)
//                                .split(",");
//                return new User(
//                                employee.getFullName(),
//                                employee.getPassword(),
//                                Collections.singletonList(new SimpleGrantedAuthority(roles[0])));
//
//                // return new org.springframework.security.core.userdetails.User(
//                // employee.getFullName(),
//                // employee.getPassword(), new ArrayList<>()
//                //// employee.isWorking(),
//                //// true, true, true,
//                //// employee.getRoleNames().stream()
//                //// .map(role -> new SimpleGrantedAuthority(role.toString()))
//                //// .collect(Collectors.toSet())
//                // );
//        }
//
//}
