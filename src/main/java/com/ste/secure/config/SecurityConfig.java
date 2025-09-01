package com.ste.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {
                    authorize
					/*
					 * .requestMatchers("/secure/swagger-ui.html").permitAll() // La page HTML
					 * principale .requestMatchers("/secure/swagger-ui/**").permitAll() //
					 * Ressources JS/CSS de Swagger UI
					 * .requestMatchers("/secure/v3/api-docs/**").permitAll() // Les définitions
					 * OpenAPI JSON/YAML .requestMatchers("/secure/webjars/**").permitAll() //
					 * .requestMatchers("/secure/error").permitAll()
					 */
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/open/*").permitAll()
                    .requestMatchers(HttpMethod.GET,"/public/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated(); 
                }).httpBasic(Customizer.withDefaults())
        		  .csrf(csrf->csrf.disable())
                  .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // Pour Spring Security 6+

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails ramesh = User.builder()
                .username("fabien")
                .password(passwordEncoder().encode("fabien"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("steve")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ramesh, admin);
    }


}
