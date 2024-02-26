package com.udacity.jwdnd.c1.review.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {


    //Dynamisch, durchläuft Spring Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        try{

        // Erlaubnis für signup für alle geben + alle restlichen Seiten erfordern Authentfizierung
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/signup").permitAll()
                        .anyRequest().authenticated()


                )
                .httpBasic(withDefaults());

    //   Konfiguration für unser Login Form / Erlaubnis für alle Besucher
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/chat", true)
                .permitAll());}

        catch (Exception e) {
            System.out.println("Security Exception!: " +e.getMessage());}

        return http.build();
    }

    //Statisch, durchläuft nicht Spring Security, performanter
   /* @Bean
    public WebSecurityCustomizer webSecurity() throws Exception{

        //ignoriert die Sicherheitsprüfung
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**");
    }*/

}