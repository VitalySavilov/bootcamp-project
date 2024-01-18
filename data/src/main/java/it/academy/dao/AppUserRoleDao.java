package it.academy.dao;

import it.academy.model.AppUserRole;

import java.util.Optional;

public interface AppUserRoleDao {

    Optional<AppUserRole> findRoleByName(String roleName);

}
