package it.academy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AppUserCreateDto(
        @NotBlank(message = "Lastname should be filled in")
        @Size(max = 40, message = "Lastname must be no longer than 40 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Lastname must contain only latin letters")
        String lastname,
        @NotBlank(message = "Firstname should be filled in")
        @Size(max = 20, message = "Firstname must be no longer than 20 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only latin letters")
        String firstname,
        @NotBlank(message = "Patronymic should be filled in")
        @Size(max = 40, message = "Patronymic must be no longer than 40 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Patronymic must contain only latin letters")
        String patronymic,
        @Email(message = "Email must be in the format of an email address")
        @NotBlank(message = "Email should be filled in")
        @Size(max = 50, message = "Email must be no longer than 40 characters")
        String email,
        @NotBlank(message = "Role should be filled in")
        @Pattern(regexp = "Administrator|Sale User|Customer User|Secure API User",
                message = "Role variants: Administrator, Sale User, Customer User, Secure API User")
        String role) {
}
