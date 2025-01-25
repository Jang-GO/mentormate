package org.janggo.mentormate.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> getClientAndRedirect(){
        return ResponseEntity.ok().body(authService.getClientAndRedirect());
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestParam("code") String code){
        // 1. 인증 코드로 액세스 토큰 요청
        String accessToken = authService.getAccessToken(code);

        // 2. 액세스 토큰으로 사용자 정보 요청
        Map<String, Object> userInfo = authService.getUserInfo(accessToken);

        // 3. 사용자 정보를 포함한 성공 응답 반환
        return ResponseEntity.ok(Map.of(
                "message", "로그인 성공",
                "userInfo", userInfo
        ));
    }
}
