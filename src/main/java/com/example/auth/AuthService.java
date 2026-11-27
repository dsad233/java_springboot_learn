package com.example.auth;

import com.example.auth.dto.request.SigninDto;
import com.example.common.dto.PayloadDto;
import com.example.common.libs.entities.User;
import com.example.common.libs.enums.Type;
import com.example.common.modules.BcryptService;
import com.example.auth.dto.request.CreateUserDto;
import com.example.common.modules.JwtService;
import com.example.common.modules.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final BcryptService bcryptService;
    private final RedisService redisService;
    private final JwtService jwtService;

    // 회원가입
    public void create(CreateUserDto createUserDto) {
        if(!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new Error("패스워드가 일치하지 않습니다. 다시 시도해 주세요.");
        }

        this.authRepository.save(User.builder().name(createUserDto.getName()).nickname(createUserDto.getNickname()).loginId(createUserDto.getLoginId()).email(createUserDto.getEmail()).password(this.bcryptService.hashPassword(createUserDto.getPassword())).build());
    }

    // 로그인
    public String login(SigninDto signinDto) {
        if (signinDto.getEmail() != null) {
            Optional<User> existEmail = this.authRepository.findByEmail(signinDto.getEmail());

            if(existEmail.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유효히지 않은 이메일 입니다. 다시 시도해 주세요.");
            }

            if(!bcryptService.comparePassword(signinDto.getPassword(), existEmail.get().getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다. 다시 시도해 주세요.");
            }

            String payloadDto = new PayloadDto(existEmail.get().getName(), existEmail.get().getNickname(), existEmail.get().getLoginId(), existEmail.get().getEmail()).toString();

            String accessToken = this.jwtService.sign(payloadDto, Type.TokenType.ACCESS.getProp());
            String refreshToken = this.jwtService.sign(payloadDto, Type.TokenType.REFRESH.getProp());

            this.redisService.set(Type.TokenType.ACCESS.getProp() + ":", refreshToken);

            return new ObjectMapper().writeValueAsString("{" + "accessToken" + ": " + accessToken + ", refreshToken: " + refreshToken + "}");
        }

        Optional<User> existLoginId = this.authRepository.findByLoginId(signinDto.getLoginId());

        if(existLoginId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유효히지 않은 로그인 아이디 입니다. 다시 시도해 주세요.");
        }

        if(!bcryptService.comparePassword(signinDto.getPassword(), existLoginId.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다. 다시 시도해 주세요.");
        }

        String payloadDto = new PayloadDto(existLoginId.get().getName(), existLoginId.get().getNickname(), existLoginId.get().getLoginId(), existLoginId.get().getEmail()).toString();

        String accessToken = this.jwtService.sign(payloadDto, Type.TokenType.ACCESS.getProp());
        String refreshToken = this.jwtService.sign(payloadDto, Type.TokenType.REFRESH.getProp());

        return new ObjectMapper().writeValueAsString("{" + "accessToken" + ": " + accessToken + ", refreshToken: " + refreshToken + "}");
    }
}
