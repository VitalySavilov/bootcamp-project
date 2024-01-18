package it.academy.dto;

public record AppUserCreateDto(
        String lastname,
        String firstname,
        String patronymic,
        String email,
        String role) {
}
