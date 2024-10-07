package com.wnsud9771.service;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wnsud9771.common.CommonResponse;
import com.wnsud9771.model.SignInResultDto;
import com.wnsud9771.model.SignUpResultDto;
import com.wnsud9771.model.User;
import com.wnsud9771.repository.UserRepository;
import com.wnsud9771.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    public SignUpResultDto signUp(String id, String password, String name, String role) {
        // TODO Auto-generated method stub
        User user;
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder().uid(id).name(name).password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN")).build();
        } else {
            user = User.builder().uid(id).name(name).password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER")).build();
        }
        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();
        if (!savedUser.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        // TODO Auto-generated method stub
        User user = userRepository.getByUid(id);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles())).build();
        setSuccessResult(signInResultDto);
        return signInResultDto;
    }
    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
