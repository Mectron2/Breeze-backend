package org.app.breeze.controller;

import lombok.AllArgsConstructor;
import org.app.breeze.DTO.AuthResponseDto;
import org.app.breeze.DTO.LoginDto;
import org.app.breeze.DTO.RegisterDTO;
import org.app.breeze.entity.User;
import org.app.breeze.exception.UserAlreadyExistsException;
import org.app.breeze.service.AuthService;
import org.app.breeze.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    private RegisterService registerService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) throws UserAlreadyExistsException {
        return new ResponseEntity<>(registerService.registerUser(registerDTO), HttpStatus.CREATED);
    }
}
