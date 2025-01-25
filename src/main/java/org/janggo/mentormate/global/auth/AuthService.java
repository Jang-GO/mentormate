package org.janggo.mentormate.global.auth;

import java.util.Map;

public interface AuthService {
    Map<String, String> getClientAndRedirect();
    String getAccessToken(String code);
    Map<String, Object> getUserInfo(String accessToken);
}
