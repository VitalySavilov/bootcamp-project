package it.academy.dao;

import it.academy.model.AppUserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppUserRoleDaoImpl implements AppUserRoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AppUserRole> findRoleByName(String roleName) {
        return entityManager.createQuery("select role from AppUserRole role where role.name=:name",
                        AppUserRole.class)
                .setParameter("name", roleName)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
