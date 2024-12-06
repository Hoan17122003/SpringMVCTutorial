package com.springmvc.tutorial.configuration;

import com.springmvc.tutorial.model.repository.order.OrderRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.springmvc.tutorial.model.repository")
public class ApplicationConfiguration {
}
