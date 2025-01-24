package org.janggo.mentormate.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final KakaoUtil kakaoUtil;

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> getClientAndRedirect(){
        Map<String, String> body = new HashMap<>();
        body.put("client-id",kakaoUtil.getClient());
        body.put("redirect-url", kakaoUtil.getRedirectUrl());
        return ResponseEntity.ok().body(body);
    }

//    @GetMapping("/login/kakao")
//    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code){
//
//    }
}
