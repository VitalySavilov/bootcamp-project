package it.academy.dao;

import it.academy.model.AppUserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppUserInfoDaoImpl implements AppUserInfoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AppUserInfo> findByEmail(String email) {
        return entityManager.createQuery("select info from AppUserInfo info where info.email=:email",
                        AppUserInfo.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
