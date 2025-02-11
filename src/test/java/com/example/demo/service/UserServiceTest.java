package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.payload.UserPayload;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserModel userModel;
    private UserDto userDto;
    private UserPayload userPayload;

    @BeforeEach
    public void setup() {
        userModel = UserModel.builder()
                .id(1L)
                .email("test@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .email("test@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        userPayload = new UserPayload("test@example.com", "password", "John", "Doe");
    }

    @Test
    public void givenUserPayload_whenCreateUser_thenReturnUserDto() {
        given(userMapper.toEntity(userPayload)).willReturn(userModel);
        given(userRepository.save(userModel)).willReturn(userModel);
        given(userMapper.toDto(userModel)).willReturn(userDto);

        UserDto savedUser = userService.createUser(userPayload);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.email()).isEqualTo(userPayload.email());
    }

    @Test
    public void givenUsers_whenGetAllUsers_thenReturnUserDtoList() {
        given(userRepository.findAll()).willReturn(List.of(userModel));
        given(userMapper.toDto(userModel)).willReturn(userDto);

        List<UserDto> users = userService.getAllUsers();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void givenUserId_whenGetUserById_thenReturnUserDto() {
        given(userRepository.findById(1L)).willReturn(Optional.of(userModel));
        given(userMapper.toDto(userModel)).willReturn(userDto);

        UserDto foundUser = userService.getUserById(1L);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.id()).isEqualTo(1L);
    }

    @Test
    public void givenInvalidUserId_whenGetUserById_thenThrowException() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void givenUserPayload_whenUpdateUser_thenReturnUpdatedUserDto() {
        given(userRepository.findById(1L)).willReturn(Optional.of(userModel));
        userModel.setEmail("updated@example.com");
        given(userRepository.save(userModel)).willReturn(userModel);
        UserDto updatedUserDto = UserDto.builder()
                .id(1L)
                .email("updated@example.com")
                .firstName("John")
                .lastName("Doe")
                .build();
        given(userMapper.toDto(userModel)).willReturn(updatedUserDto);
        userPayload = new UserPayload("updated@example.com", "password", "John", "Doe");

        UserDto updatedUser = userService.updateUser(1L, userPayload);

        assertThat(updatedUser.email()).isEqualTo(userPayload.email());
    }

    @Test
    public void givenInvalidUserId_whenUpdateUser_thenThrowException() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        RuntimeException runtimeException = assertThrows(ResourceNotFoundException.class, () ->
                userService.updateUser(1L, userPayload));

        assertEquals("User not found", runtimeException.getMessage());
        verify(userRepository, never()).save(any(UserModel.class));
    }

    @Test
    public void givenUserId_whenDeleteUser_thenVerifyDeletion() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}