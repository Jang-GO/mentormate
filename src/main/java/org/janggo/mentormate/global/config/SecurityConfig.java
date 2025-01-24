package org.janggo.mentormate.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .oauth2Login(oauth2 ->
                        oauth2
                                .defaultSuccessUrl("/login/success", true) // 카카오 인증 성공 후 리다이렉트 경로
                );;
        return http.build();
    }
}
