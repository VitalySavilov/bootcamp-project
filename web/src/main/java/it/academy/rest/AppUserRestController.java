package it.academy.rest;

import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserRestController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserReadDto> postUser(@RequestBody AppUserCreateDto appUserCreateDto) {
        return new ResponseEntity<>(
                appUserService.createAppUser(appUserCreateDto),
                HttpStatus.CREATED);
    }
}
