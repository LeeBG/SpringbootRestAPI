package com.rest.api.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 예외 처리
 * 1. jwt토큰 없이 api를 호출했을 경우
 * 2. 형식이 맞지 않거나 만료된 jwt토큰으로 api를 호출했을 경우
 * 커스텀 예외 처리는 ControllerAdvice즉 Spring이 처리 가능한 영역까지 request가 도달해야 처리가능하다.
 * 커스텀 처리가 되지 않는 이유는 Spring Security는 Spring앞단에서 필터 처리를 하기 때문에
 * 1,2번 상황의 exception이 Spring에 도달하지 않는다.(필터링의 순서 문제)
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {//SpringSecurity에서 제공하는 AuthenticationEntryPoint상속
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.sendRedirect("/exception/entrypoint");
    }
}
