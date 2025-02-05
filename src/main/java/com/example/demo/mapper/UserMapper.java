package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserModel;
import com.example.demo.payload.UserPayload;
import com.example.demo.util.DateUtil;
import org.springframework.stereotype.Component;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */
@Component
public class UserMapper {
    public UserDto toDto(UserModel user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdDatetime(DateUtil.toPrettyFormat(user.getCreatedDatetime()))
                .updatedDatetime(DateUtil.toPrettyFormat(user.getUpdatedDatetime()))
                .build();
    }


    public UserModel toEntity(UserPayload userPayload) {
        return UserModel.builder()
                .email(userPayload.email())
                .password(userPayload.password())
                .firstName(userPayload.firstName())
                .lastName(userPayload.lastName())
                .build();
    }
}
