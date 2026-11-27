package com.example.auth;

import com.example.common.dto.ResponseDto;
import com.example.auth.dto.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> create(@RequestBody CreateUserDto createUserDto) {
        this.authService.create(createUserDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(HttpStatus.CREATED.value());
        responseDto.setMessage("success");
        return ResponseEntity.ok().body(responseDto);
    }
}
