package org.app.breeze.controller;

import org.app.breeze.DTO.PostDto;
import org.app.breeze.DTO.UserDTO;
import org.app.breeze.entity.User;
import org.app.breeze.exception.ResourceNotFoundException;
import org.app.breeze.repository.PostRepository;
import org.app.breeze.repository.UserRepository;
import org.app.breeze.service.PostService;
import org.app.breeze.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PostRepository postRepository;
    private final PostService postService;

    public UserController(UserRepository userRepository, UserService userService, PostRepository postRepository, PostService postService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<UserDTO> getUserInfoByID(@PathVariable long userId) {
        UserDTO user = userRepository.findById(userId)
                .map(userService::convertToUserDto).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<PostDto> postDtos = postRepository.findByUserId(user.getId())
                .stream()
                .map(postService::convertToPostDto)
                .collect(Collectors.toList());

        user.setPostDtoList(postDtos);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserInfoByUsername(@PathVariable String username) {
        UserDTO user = userRepository.findByUsername(username).map(userService::convertToUserDto).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<PostDto> postDtos = postRepository.findByUserId(user.getId())
                .stream()
                .map(postService::convertToPostDto)
                .collect(Collectors.toList());

        user.setPostDtoList(postDtos);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("WhoAmI")
    public ResponseEntity<UserDTO> whoAmI(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        UserDTO currentUser = userService.convertToUserDto(userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User not found")));
        return ResponseEntity.ok(currentUser);
    }
}