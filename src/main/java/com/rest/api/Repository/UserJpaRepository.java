package com.rest.api.Repository;

import com.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findByUid(String email);     //가입한 이메일 조회
    Optional<User> findByUidAndProvider(String uid, String provider); //uid 및 provider 절보로 회원 조회
}
