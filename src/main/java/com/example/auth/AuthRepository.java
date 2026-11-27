package com.example.auth;

import com.example.common.libs.entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, String> {
    // 로그인 아이디 유무 조회
    @Query("select u.loginId, u.password from User u where u.loginId = :loginId")
    Optional<User> findByLoginId(@Param("loginId") String loginId);

    // 이메일 존재 유무 조회
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
