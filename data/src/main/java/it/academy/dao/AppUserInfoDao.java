package it.academy.dao;

import it.academy.model.AppUserInfo;

import java.util.Optional;

public interface AppUserInfoDao {

    Optional<AppUserInfo> findByEmail(String email);

}
