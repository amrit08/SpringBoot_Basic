package com.apj.electronic.store.config;
import com.apj.electronic.store.security.JwtAuthenticationEntryPoint;
import com.apj.electronic.store.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

   private  final  String[] PUBLIC_URLS= {

           "/swagger-ui/**",
           "/webjars/**",
           "/swagger-resources/**",
           "/v3/api-docs",
           "/v2/api-docs",
           "/test"

    };




    //IN MEMORY AUTHENTICATION

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        //users create
//        //InMemoryDeatailsManager-is implementation class of UserDetailsService
//
//        UserDetails normalUser = User.builder()
//                .username("DALTON PANDIT")
//                .password(passwordEncoder().encode("dalton pandit"))
//                .roles("NORMAL USER")
//                .build();
//
//        UserDetails adminUser = User.builder()
//                .username("AMRIT")
//                .password(passwordEncoder().encode("amrit"))
//                .roles("ADMIN")
//                .build();
//        return  new InMemoryUserDetailsManager(normalUser,adminUser);
//    }

    //AUTHENTICATION FROM DATABASE
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    //FORM BASED LOGIN

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //FORM BASED LOGIN AUTHENTICATION
//        http.authorizeRequests()
//                .anyRequest().authenticated().and()
//                .formLogin()
//                .loginPage("login.html")
//                .loginProcessingUrl("/process-url")
//                .defaultSuccessUrl("/dashboard")
//                .failureUrl("error")
//                .and()
//                .logout()
//                .logoutUrl("/logout");
//
//

        //BASIC AUTHENTICATION
//        http.csrf()
//                .disable()
//                .cors()
//                .disable().
//                authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();

//JWT AUTHENTICATION

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/login")
                .permitAll()
                .antMatchers("/auth/google")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/users")
                .permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
                .antMatchers(PUBLIC_URLS)
                .permitAll()
                .antMatchers(HttpMethod.GET)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
       return  http.build();

   }


    @Bean
    public PasswordEncoder passwordEncoder()
    {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
    {
        return builder.getAuthenticationManager();

    }

    //CORS Configuration
    @Bean
    public FilterRegistrationBean corsFilter()
    {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfigurations = new CorsConfiguration();
        corsConfigurations.setAllowCredentials(true);
      //  corsConfigurations.setAllowedOrigins(Arrays.asList("http://domain2.com","http://localhost:4200"));
        corsConfigurations.addAllowedOriginPattern("*");
        corsConfigurations.addAllowedHeader("Authorization");
        corsConfigurations.addAllowedHeader("Content-Type");
        corsConfigurations.addAllowedHeader("Accept");
        corsConfigurations.addAllowedMethod("GET");
        corsConfigurations.addAllowedMethod("POST");
        corsConfigurations.addAllowedMethod("DELETE");
        corsConfigurations.addAllowedMethod("PUT");
        corsConfigurations.addAllowedMethod("OPTIONS");
        corsConfigurations.setMaxAge(3600L);

        source.registerCorsConfiguration("/**",corsConfigurations);

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));

        filterRegistrationBean.setOrder(-110);

        return  filterRegistrationBean;

    }

}
