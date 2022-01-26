package com.rest.api.controller.v1;

import com.rest.api.Repository.UserJpaRepository;
import com.rest.api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor        // class상단에 선언하면 class내부에 final이나 @notnull로 선언된 객체에 대해서 생성자 주입을 수행
@RestController
@RequestMapping(value = "v1")
public class UserController {
    private final UserJpaRepository userJpaRepository;

    @GetMapping("/user")
    public List<User> findAllUser(){
        return userJpaRepository.findAll();
    }

    @PostMapping("/user")
    public User save(){
        User userEntity = User.builder()
                .uid("yumi@naver.com")
                .name("유미")
                .build();
        return userJpaRepository.save(userEntity);
    }
}
