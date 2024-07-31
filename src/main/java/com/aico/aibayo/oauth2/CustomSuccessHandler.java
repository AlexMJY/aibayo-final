package com.aico.aibayo.oauth2;

import com.aico.aibayo.dto.member.CustomOAuth2Member;
import com.aico.aibayo.jwt.JWTUtil;
import com.aico.aibayo.service.member.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger LOGGER = Logger.getLogger(CustomSuccessHandler.class.getName());
    private final JWTUtil jwtUtil;
    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 인증된 사용자 정보를 가져옵니다.
        CustomOAuth2Member customOAuth2User = (CustomOAuth2Member) authentication.getPrincipal();
        String username = customOAuth2User.getUsername();

        // 사용자 권한을 가져옵니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // JWT를 생성합니다.
        String token = jwtUtil.createJwt(username, role, 60 * 60 * 10L);
        LOGGER.info("Generated JWT Token: " + token);

        // JWT를 서버에 저장
        tokenService.saveToken(token);

        // 생성된 JWT를 쿠키에 담아 응답에 추가
        Cookie jwtCookie = createCookie("jwt", token);
        response.addCookie(jwtCookie);
        LOGGER.info("JWT Cookie added: " + jwtCookie.getValue());

        // 인증 성공 후 리디렉트할 URL로 이동
//        response.sendRedirect("/main/admin");
        if (role.equals("ROLE_ADMIN")) {
            response.sendRedirect("/main/admin");
        } else if (role.equals("ROLE_USER")) {
            response.sendRedirect("/main/user");
        } else {
            response.sendRedirect("/login?error");
        }

    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 10); // 쿠키 유효 기간 설정 (초 단위)
        cookie.setSecure(false); // 로컬 환경에서는 false로 설정, 프로덕션에서는 true로 설정
        cookie.setPath("/"); // 쿠키의 경로 설정
        cookie.setHttpOnly(true); // HTTP 전용 쿠키로 설정 (자바스크립트에서 접근 불가)
        return cookie;
    }
}