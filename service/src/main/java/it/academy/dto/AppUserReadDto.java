package it.academy.dto;

public record AppUserReadDto(
        String fullName,
        String email,
        String role) {
}
