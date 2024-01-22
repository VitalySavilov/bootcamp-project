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
    private static final int PAGE_SIZE = 10;

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

    @GetMapping
    public ResponseEntity<List<AppUserReadDto>> getUsersPage(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        return new ResponseEntity<>(
                appUserService.getUsersPage(pageNumber, PAGE_SIZE),
                HttpStatus.OK);
    }

}
