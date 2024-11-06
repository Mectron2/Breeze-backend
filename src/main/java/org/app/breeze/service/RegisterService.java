package org.app.breeze.service;

import lombok.AllArgsConstructor;
import org.app.breeze.DTO.RegisterDTO;
import org.app.breeze.entity.User;
import org.app.breeze.entity.UserRole;
import org.app.breeze.exception.UserAlreadyExistsException;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.repository.UserRolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegisterService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRolesRepository userRolesRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User registerUser(RegisterDTO registerDTO) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with that email already exists.");
        } else if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UserAlreadyExistsException("A user with that username already exists.");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPasswordHash(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(1L);
        userRolesRepository.save(userRole);

        return user;
    }
}
