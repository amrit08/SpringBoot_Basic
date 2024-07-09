package com.amrit.core.config;

import com.amrit.core.couple.Samosa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean(name = "samosa1")
	public Samosa getSamosa1(){

		return new Samosa("Tandoori");
	}
	@Bean(name = "samosa2")
	public Samosa getSamosa2(){

		return new Samosa("momo");
	}

}
