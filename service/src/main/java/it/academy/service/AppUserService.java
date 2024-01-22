package it.academy.service;

import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;

import java.util.List;

public interface AppUserService {

    AppUserReadDto createAppUser(AppUserCreateDto appUserCreateDto);

    List<AppUserReadDto> getAllUsers();

    List<AppUserReadDto> getUsersPage(int pageNumber, int pageSize);

}
