package it.academy;

import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestPropertySource(value = "classpath:/test.db.properties")
class AppUserServiceImplTest {

    @Autowired
    AppUserService appUserService;

    @Test
    void createAppUser() {
        AppUserCreateDto appUserCreateDto = new AppUserCreateDto(
                "Alexandrov",
                "Alexandr",
                "Alexandrovich",
                "Alex@gmail.com",
                "Administrator");
        String fullName = new StringJoiner(" ")
                .add(appUserCreateDto.lastname())
                .add(appUserCreateDto.firstname())
                .add(appUserCreateDto.patronymic())
                .toString();
        AppUserReadDto result = appUserService.createAppUser(appUserCreateDto);
        assertAll(
                () -> assertEquals(fullName, result.fullName()),
                () -> assertEquals(appUserCreateDto.email(), result.email()),
                () -> assertEquals(appUserCreateDto.role(), result.role()));
    }
}
