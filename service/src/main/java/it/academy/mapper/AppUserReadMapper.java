package it.academy.mapper;

import it.academy.dto.AppUserReadDto;
import it.academy.model.AppUser;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class AppUserReadMapper implements Mapper<AppUser, AppUserReadDto> {

    @Override
    public AppUserReadDto map(AppUser object) {
        return new AppUserReadDto(new StringJoiner(" ")
                .add(object.getAppUserInfo().getLastname())
                .add(object.getAppUserInfo().getFirstname())
                .add(object.getAppUserInfo().getPatronymic())
                .toString(),
                object.getAppUserInfo().getEmail(),
                object.getRole().getName()
        );
    }
}
