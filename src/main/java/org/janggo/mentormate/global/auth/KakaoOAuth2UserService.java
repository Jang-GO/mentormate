package org.janggo.mentormate.global.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.janggo.mentormate.global.auth.jwt.JwtProvider;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {
    private final JwtProvider jwtProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 카카오 ID 및 닉네임 추출
        String kakaoId = attributes.get("id").toString();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        log.info("kakao info - {}", attributes);
        String nickname = ((Map<String, Object>) kakaoAccount.get("profile")).get("nickname").toString();

        log.info("카카오 로그인 성공 - ID: {}, 닉네임: {}", kakaoId, nickname);

        // JWT 발급
        String jwt = jwtProvider.createToken(kakaoId);
        log.info("발급된 JWT: {}", jwt);

        return oAuth2User; // 추후 커스텀 유저 객체로 변경 가능
    }
}
