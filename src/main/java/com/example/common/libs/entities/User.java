package com.example.common.libs.entities;

import com.example.common.libs.entities.abs.AbsDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorValue("USER")
public class User extends AbsDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(comment = "유저 이름")
    String name;

    @Column(comment = "유저 닉네임")
    String nickname;

    @Column(name = "login_id", comment = "유저 로그인 아이디")
    String loginId;

    @Column(comment = "유저 이메일")
    String email;

    @Column(comment = "유저 패스워드")
    String password;
}
