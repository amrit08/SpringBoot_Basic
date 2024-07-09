package org.example.config;

import org.example.web.HomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public HomeController homeController(){
         return  new HomeController();

    }

}
