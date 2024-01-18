package it.academy.service;

import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;

public interface AppUserService {

    AppUserReadDto createAppUser(AppUserCreateDto appUserCreateDto);

}
