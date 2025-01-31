package org.janggo.mentormate.global.config;

import lombok.RequiredArgsConstructor;
import org.janggo.mentormate.global.auth.KakaoOAuth2UserService;
import org.janggo.mentormate.global.auth.jwt.JwtAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final KakaoOAuth2UserService kakaoOAuth2UserService;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .oauth2Login(oauth2 ->
                        oauth2
                                .authorizationEndpoint(end -> end.baseUri("/auth/login"))
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(kakaoOAuth2UserService)));


        return http.build();
    }

}
