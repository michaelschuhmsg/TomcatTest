package com.example.tomcat_artefact.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tomcat_artefact.entity.TestServletLogging;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<TestServletLogging> exampleServletBean() {
        ServletRegistrationBean<TestServletLogging> bean =
            new ServletRegistrationBean<>(
                new TestServletLogging(),  // Instantiate the Servlet
                "/test"  // Specify the URL mapping
            );
        bean.setLoadOnStartup(1);
        return bean;
    }
}

