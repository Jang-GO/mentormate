package org.janggo.mentormate.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KakaoUtil kakaoUtil;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Override
    public Map<String, String> getClientAndRedirect() {
        Map<String, String> body = new HashMap<>();
        body.put("client-id",kakaoUtil.getClient());
        body.put("redirect-url", kakaoUtil.getRedirectUrl());
        return body;
    }

    @Override
    public String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format(
                "grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                kakaoUtil.getClient(), kakaoUtil.getRedirectUrl(), code
        );

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                kakaoUtil.getTokenUri(), HttpMethod.POST, request, String.class
        );

        try {
            Map<String, Object> responseBody = objectMapper.readValue(response.getBody(), Map.class);
            return responseBody.get("access_token").toString();
        } catch (Exception e) {
            throw new RuntimeException("액세스 토큰 요청 중 오류 발생", e);
        }
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                kakaoUtil.getUserInfoUri(), HttpMethod.GET, request, Map.class
        );

        return response.getBody();
    }
}
