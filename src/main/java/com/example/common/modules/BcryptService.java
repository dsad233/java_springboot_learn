package com.example.common.modules;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class BcryptService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePassword(String password, String hashPassword) {
        return bCryptPasswordEncoder.matches(password, hashPassword);
    }
}
