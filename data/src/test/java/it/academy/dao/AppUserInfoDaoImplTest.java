package it.academy.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import it.academy.DataConfig;
import it.academy.model.AppUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@TestPropertySource(value = "classpath:/test.db.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DbUnitConfiguration(databaseConnection="dataSource")
class AppUserInfoDaoImplTest {

    @Autowired
    private AppUserInfoDao appUserInfoDao;

    @Test
    @DatabaseSetup(connection="dataSource", value = "classpath:db/user.xml")
    @DatabaseTearDown(connection="dataSource", value = "classpath:db/user.xml", type = DatabaseOperation.DELETE_ALL)
    void findByEmail() {
        String email = "ivan@gmail.com";
        String firstName = "Ivan";
        String lastName = "Ivanov";
        Optional<AppUserInfo> actualResult = appUserInfoDao.findByEmail(email);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> assertAll(
                () -> assertEquals(firstName, result.getFirstname()),
                () -> assertEquals(lastName, result.getLastname()),
                () -> assertEquals(email, result.getEmail())
        ));
    }
}
