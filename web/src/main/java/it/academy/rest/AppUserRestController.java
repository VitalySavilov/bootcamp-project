package it.academy.rest;

import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserRestController {
    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserReadDto> postUser(@RequestBody @Validated AppUserCreateDto appUserCreateDto) {
        return new ResponseEntity<>(
                appUserService.createAppUser(appUserCreateDto),
                HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<AppUserReadDto>> getAllUsers() {
        return new ResponseEntity<>(
                appUserService.getAllUsers(),
                HttpStatus.OK);
    }

}
