package com.example.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "이름 란을 입력해 주세요.")
    String name;
    @NotBlank(message = "닉네임 란을 입력해 주세요.")
    String nickname;
    @NotBlank(message = "로그인 ID 란을 입력해 주세요.")
    String loginId;
    @Email(message = "이메일 형식이 올바르지 않습니다. 다시 시도해 주세요.")
    String email;
    @NotBlank(message = "패스워드 란을 입력해 주세요.")
    String password;
    @NotBlank(message = "패스워드 확인 란을 입력해 주세요.")
    String confirmPassword;
}
