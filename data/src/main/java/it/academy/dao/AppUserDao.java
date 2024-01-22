package it.academy.dao;

import it.academy.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    AppUser save(AppUser appUser);

    Optional<AppUser> findById(Long id);

    List<AppUser> findAll();

    List<AppUser> findUsers(int firstResult, int pageSize);

}
