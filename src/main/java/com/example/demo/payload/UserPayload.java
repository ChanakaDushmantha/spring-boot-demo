package com.example.demo.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Dushmantha
 * @email dushmantha.sse@gmail.com
 */

public record UserPayload(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        @Schema(description = "User's email address", example = "user@example.com")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        @Schema(description = "User's password", example = "password1234")
        String password,

        @NotBlank(message = "First name cannot be blank")
        @Size(max = 20, message = "First name can not be more than 20 characters")
        @Schema(description = "User's first name", example = "John")
        String firstName,

        @Size(max = 20, message = "Last name can not be more than 20 characters")
        @Schema(description = "User's last name", example = "Doe")
        String lastName
) {
}

