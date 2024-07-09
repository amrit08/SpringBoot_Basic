package org.example.config;

import org.example.beans.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example"})
public class AppConfig {
    //bean for cartService
    @Bean("cartService1")
    public CartService cartService(){

        CartService cartService=new CartService();
        cartService.setName("abhi");
        return cartService;
        //return new CartService();
    }



}
