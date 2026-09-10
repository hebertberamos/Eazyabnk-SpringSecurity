package com.sec.authenticator.config;

import com.sec.authenticator.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Profile("!prod")
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession"))
                .redirectToHttps(https -> https.disable()) // Only HTTP.
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession", "/.well-known/appspecific/com.chrome.devtools.json", "/invalidSession").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(/*PasswordEncoder encoder*/) {
//        UserDetails user = User.withUsername("user").password("{noop}12345").roles("read").build();
//        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$lZpDHfNFf0uhcQwtATB5tupqaPjaypc97Ux.a4tLdR31NNGy1x15.").roles("admin").build(); //encrypted password: 54321
//        return new InMemoryUserDetailsManager(user, admin);
//    }

   /* @Bean
    public UserDetailsService userDetailsService(DataSource source) {
        return new JdbcUserDetailsManager(source);
    }*/

}
