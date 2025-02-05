package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String createdDatetime,
        String updatedDatetime
) {
}
