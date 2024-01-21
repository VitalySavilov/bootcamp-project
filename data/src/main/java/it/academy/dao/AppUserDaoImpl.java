package it.academy.dao;

import it.academy.model.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AppUser save(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Override
    public List<AppUser> findAll() {
        return entityManager.createQuery(
                        "select u from AppUser u order by u.appUserInfo.email", AppUser.class)
                .getResultList();
    }

}
