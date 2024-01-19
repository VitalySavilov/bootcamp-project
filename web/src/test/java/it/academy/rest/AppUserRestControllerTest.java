package it.academy.rest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import it.academy.WebApplication;
import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "classpath:test.application.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
class AppUserRestControllerTest {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    @DatabaseSetup(value = "classpath:db/clean.xml")
    @DatabaseTearDown(value = "classpath:db/clean.xml", type = DatabaseOperation.DELETE_ALL)
    void postUser() {
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

        HttpEntity<AppUserCreateDto> entity = new HttpEntity<>(appUserCreateDto, headers);

        ResponseEntity<AppUserReadDto> response = restTemplate.exchange(
                createURLWithPort("/api/v1/users"),
                HttpMethod.POST, entity, AppUserReadDto.class);

        AppUserReadDto result = response.getBody();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(fullName, result.fullName()),
                () -> assertEquals(appUserCreateDto.email(), result.email()),
                () -> assertEquals(appUserCreateDto.role(), result.role()));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}