package it.academy.dao;

import it.academy.DataConfig;
import it.academy.model.AppUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Transactional
@TestPropertySource(value = "classpath:/test.db.properties")
class AppUserInfoDaoImplTest {

    @Autowired
    private AppUserInfoDao appUserInfoDao;

    @Test
    void findByEmail() {
        String email = "petr@gmail.com";
        String firstName = "Petr";
        String lastName = "Petrov";
        Optional<AppUserInfo> actualResult = appUserInfoDao.findByEmail(email);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> assertAll(
                () -> assertEquals(firstName, result.getFirstname()),
                () -> assertEquals(lastName, result.getLastname()),
                () -> assertEquals(email, result.getEmail())
        ));
    }
}
