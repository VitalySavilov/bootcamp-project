package it.academy;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfig.class)
@Transactional
@TestPropertySource(value = "classpath:/test.db.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
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

    @Test
    @DatabaseSetup(value = "classpath:db/users.xml")
    @DatabaseTearDown(value = "classpath:db/users.xml", type = DatabaseOperation.DELETE_ALL)
    void getAllUsers() {
        int expectedCount = 5;
        List<AppUserReadDto> resultList = appUserService.getAllUsers();
        assertEquals(expectedCount, resultList.size());
    }

    @Test
    @DatabaseSetup(value = "classpath:db/users.xml")
    @DatabaseTearDown(value = "classpath:db/users.xml", type = DatabaseOperation.DELETE_ALL)
    void getUsersPage() {
        int pageNumber = 0;
        int pageSize = 4;
        List<AppUserReadDto> resultList = appUserService.getUsersPage(pageNumber, pageSize);
        assertEquals(pageSize, resultList.size());
    }

}
