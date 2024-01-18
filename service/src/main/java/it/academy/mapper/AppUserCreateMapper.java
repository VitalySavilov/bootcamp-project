package it.academy.mapper;

import it.academy.dto.AppUserCreateDto;
import it.academy.model.AppUser;
import it.academy.model.AppUserInfo;
import org.springframework.stereotype.Component;

@Component
public class AppUserCreateMapper implements Mapper<AppUserCreateDto, AppUser> {

    @Override
    public AppUser map(AppUserCreateDto object) {
        return AppUser.builder()
                .appUserInfo(AppUserInfo.builder()
                        .lastname(object.lastname())
                        .firstname(object.firstname())
                        .patronymic(object.patronymic())
                        .email(object.email())
                        .build())
                .build();
    }
}
