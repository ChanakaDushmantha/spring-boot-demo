package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.payload.UserPayload;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserPayload userPayload) {
        UserModel user = userMapper.toEntity(userPayload);
        user = userRepository.save(user);

        log.info("User saved successfully");
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserPayload userPayload) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existingUser.setEmail(userPayload.email());
        existingUser.setPassword(userPayload.password());
        existingUser.setFirstName(userPayload.firstName());
        existingUser.setLastName(userPayload.lastName());
        userRepository.save(existingUser);

        log.info("User updated successfully");
        return userMapper.toDto(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
