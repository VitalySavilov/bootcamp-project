package it.academy.dao;

import it.academy.model.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserDaoImpl implements AppUserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AppUser save(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

}
