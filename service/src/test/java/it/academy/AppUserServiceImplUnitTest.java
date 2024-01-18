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
import it.academy.service.AppUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplUnitTest {
    @Mock
    AppUserDao appUserDao;
    @Mock
    AppUserInfoDao appUserInfoDao;
    @Mock
    AppUserRoleDao appUserRoleDao;
    @Mock
    AppUserCreateMapper appUserCreateMapper;
    @Mock
    AppUserReadMapper appUserReadMapper;
    @InjectMocks
    AppUserServiceImpl appUserService;

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
        AppUserReadDto appUserReadDto = new AppUserReadDto(
                fullName,
                appUserCreateDto.email(),
                appUserCreateDto.role());

        doReturn(Optional.empty()).when(appUserInfoDao).findByEmail(appUserCreateDto.email());
        doReturn(appUser).when(appUserCreateMapper).map(appUserCreateDto);
        doReturn(Optional.of(appUserRole)).when(appUserRoleDao).findRoleByName(appUserCreateDto.role());
        doReturn(appUser).when(appUserDao).save(appUser);
        doReturn(appUserReadDto).when(appUserReadMapper).map(appUser);

        AppUserReadDto result = appUserService.createAppUser(appUserCreateDto);
        assertAll(
                () -> assertEquals(fullName, result.fullName()),
                () -> assertEquals(appUserCreateDto.email(), result.email()),
                () -> assertEquals(appUserCreateDto.role(), result.role()));
    }

}
