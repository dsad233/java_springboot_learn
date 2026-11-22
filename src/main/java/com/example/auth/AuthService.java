package com.example.auth;

import com.example.common.libs.entities.User;
import com.example.common.utils.BcryptService;
import com.example.auth.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final BcryptService bcryptService;

    // 회원가입
    public void create(CreateUserDto createUserDto) {
        if(!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new Error("패스워드가 일치하지 않습니다. 다시 시도해 주세요.");
        }

        this.authRepository.save(User.builder().name(createUserDto.getName()).nickname(createUserDto.getNickname()).loginId(createUserDto.getLoginId()).email(createUserDto.getEmail()).password(this.bcryptService.hashPassword(createUserDto.getPassword())).build());
    }
}
