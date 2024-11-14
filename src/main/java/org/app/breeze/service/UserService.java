package org.app.breeze.service;

import org.app.breeze.DTO.UserDTO;
import org.app.breeze.entity.User;
import org.app.breeze.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public UserDTO convertToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setProfileImagePath(user.getProfileImagePath());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
