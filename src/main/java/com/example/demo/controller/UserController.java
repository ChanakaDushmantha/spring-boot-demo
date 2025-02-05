package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.payload.UserPayload;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserPayload userPayload) {
        log.info("Create user");
        log.debug("User payload: {}", userPayload);
        return ResponseEntity.ok(userService.createUser(userPayload));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("Get user by id: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserPayload userPayload) {
        log.info("Update user by id: {}", id);
        log.debug("User payload: {}", userPayload);
        return ResponseEntity.ok(userService.updateUser(id, userPayload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("User delete by id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}



