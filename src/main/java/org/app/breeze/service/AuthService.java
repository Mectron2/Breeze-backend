package org.app.breeze.service;

import org.app.breeze.DTO.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}