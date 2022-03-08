package com.rest.api.service;

import com.rest.api.Repository.UserJpaRepository;
import com.rest.api.advice.exception.CUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//토큰에 세팅된 User정보로 회원 정보를 조회하는 UserDetailsService를 재정의 할 것이다.
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk){
        return userJpaRepository.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
