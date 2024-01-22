package it.academy.service;

import it.academy.dao.AppUserDao;
import it.academy.dao.AppUserInfoDao;
import it.academy.dao.AppUserRoleDao;
import it.academy.dto.AppUserCreateDto;
import it.academy.dto.AppUserReadDto;
import it.academy.exception.EmailAlreadyExistException;
import it.academy.mapper.AppUserCreateMapper;
import it.academy.mapper.AppUserReadMapper;
import it.academy.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppUserServiceImpl implements AppUserService {
    private final AppUserDao appUserDao;
    private final AppUserInfoDao appUserInfoDao;
    private final AppUserRoleDao appUserRoleDao;
    private final AppUserCreateMapper appUserCreateMapper;
    private final AppUserReadMapper appUserReadMapper;

    @Override
    @Transactional
    public AppUserReadDto createAppUser(AppUserCreateDto appUserCreateDto) {
        appUserInfoDao.findByEmail(appUserCreateDto.email()).ifPresent(s -> {
            throw new EmailAlreadyExistException(new StringBuilder() //todo test
                    .append("User with email: \"")
                    .append(appUserCreateDto.email())
                    .append("\" already exists")
                    .toString());
        });
        AppUser appUser = appUserCreateMapper.map(appUserCreateDto);
        appUserRoleDao.findRoleByName(appUserCreateDto.role()).ifPresent(appUser::setRole);
        return appUserReadMapper.map(appUserDao.save(appUser));
    }

    @Override
    public List<AppUserReadDto> getAllUsers() {
        return appUserDao.findAll().stream()
                .map(appUserReadMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppUserReadDto> getUsersPage(int pageNumber, int pageSize) {
        int firstResult = pageNumber * pageSize;
        return appUserDao.findUsers(firstResult, pageSize).stream()
                .map(appUserReadMapper::map)
                .collect(Collectors.toList());
    }

}
