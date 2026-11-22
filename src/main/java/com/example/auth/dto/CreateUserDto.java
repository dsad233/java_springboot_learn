package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    String name;
    String nickname;
    String loginId;
    String email;
    String password;
    String confirmPassword;
}
