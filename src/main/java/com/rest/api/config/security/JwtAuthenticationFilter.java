package com.rest.api.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
    Jwt가 유효한 토큰인지 인증하기 위한 Filter
    Security설정 시 UsernamePasswordAuthenticationFilter앞에 세팅할 것이다.
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    //Jwt Provider 주입하기
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 JWT Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);
        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication auth = jwtTokenProvider.getAuthentication(token); //Authentication자체는 인증된 정보이다.
            SecurityContextHolder.getContext().setAuthentication(auth);
            // 인증된 사용자 정보인 Principal은 Aythentication에서 관리하고, Authentication은
            // SecurityContext에서 관리하고 SecurityContext는 SecurityContextHolder에서 관리한다.

        }
        chain.doFilter(request, response);
    }
}
