package org.janggo.mentormate.global.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component  // Spring 관리 빈으로 등록
public class KakaoUtil {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String client;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUrl;
}
