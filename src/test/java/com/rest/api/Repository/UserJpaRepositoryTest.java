package com.rest.api.Repository;

import com.rest.api.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepositoryTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByid_thenReturnUser(){
        String uid = "angrydaddy@gmail.com";
        String name = "angrydaddy";
        //given
        userJpaRepository.save(User.builder()
                .uid(uid)
                .password(passwordEncoder.encode("1234"))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        //when
        Optional<User> user = userJpaRepository.findByUid(uid);
        assertNotNull(user); // user객체가 null이 아닌지 체크
        assertTrue(user.isPresent()); //user객체가 존재여부 true/false체크
        assertEquals(user.get().getName(),name); // user객체의 name과 name변수 값이 같은지 체크
        assertThat(user.get().getName(),is(name)); //user객체의 name과 name변수 값이 같은지 체크
    }
}