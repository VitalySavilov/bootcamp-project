package it.academy;

import it.academy.dao.AppUserDao;
import it.academy.dao.AppUserInfoDao;
import it.academy.dao.AppUserRoleDao;
import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.mapper.AppUserCreateMapper;
import it.academy.mapper.AppUserReadMapper;
import it.academy.model.AppUser;
import it.academy.model.AppUserInfo;
import it.academy.model.AppUserRole;
import it.academy.service.AppUserService;
import it.academy.service.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppUserServiceImplUnitTest {
    AppUserService appUserService;

    AppUserDao appUserDao;
    AppUserInfoDao appUserInfoDao;
    AppUserRoleDao appUserRoleDao;
    AppUserCreateMapper appUserCreateMapper;
    AppUserReadMapper appUserReadMapper;

    @BeforeEach
    void init() {
        this.appUserDao = Mockito.mock(AppUserDao.class);
        this.appUserInfoDao = Mockito.mock(AppUserInfoDao.class);
        this.appUserRoleDao = Mockito.mock(AppUserRoleDao.class);
        this.appUserCreateMapper = Mockito.mock(AppUserCreateMapper.class);
        this.appUserReadMapper = Mockito.mock(AppUserReadMapper.class);
        this.appUserService = new AppUserServiceImpl(
                appUserDao,
                appUserInfoDao,
                appUserRoleDao,
                appUserCreateMapper,
                appUserReadMapper);
    }

    @Test
    void createAppUser() {
        AppUserCreateDto appUserCreateDto = new AppUserCreateDto(
                "Alexandrov",
                "Alexandr",
                "Alexandrovich",
                "Alex@gmail.com",
                "Administrator");
        AppUser appUser = AppUser.builder()
                .appUserInfo(AppUserInfo.builder()
                        .lastname(appUserCreateDto.lastname())
                        .firstname(appUserCreateDto.firstname())
                        .patronymic(appUserCreateDto.patronymic())
                        .email(appUserCreateDto.email())
                        .build())
                .build();
        AppUserRole appUserRole = AppUserRole.builder()
                .name(appUserCreateDto.role())
                .build();
        String fullName = new StringJoiner(" ")
                .add(appUserCreateDto.lastname())
                .add(appUserCreateDto.firstname())
                .add(appUserCreateDto.patronymic())
                .toString();
        AppUserReadDto appUserReadDto = new AppUserReadDto(fullName, appUserCreateDto.email(), appUserCreateDto.role());

        Mockito.doReturn(Optional.empty()).when(appUserInfoDao).findByEmail(appUserCreateDto.email());
        Mockito.doReturn(appUser).when(appUserCreateMapper).map(appUserCreateDto);
        Mockito.doReturn(Optional.of(appUserRole)).when(appUserRoleDao).findRoleByName(appUserCreateDto.role());
        Mockito.doReturn(appUser).when(appUserDao).save(appUser);
        Mockito.doReturn(appUserReadDto).when(appUserReadMapper).map(appUser);

        AppUserReadDto result = appUserService.createAppUser(appUserCreateDto);
        assertAll(
                () -> assertEquals(fullName, result.fullName()),
                () -> assertEquals(appUserCreateDto.email(), result.email()),
                () -> assertEquals(appUserCreateDto.role(), result.role()));
    }

}
