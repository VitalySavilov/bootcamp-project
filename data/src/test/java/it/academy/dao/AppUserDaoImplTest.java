package it.academy.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import it.academy.DataConfig;
import it.academy.model.AppUser;
import it.academy.model.AppUserInfo;
import it.academy.model.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Transactional
@TestPropertySource(value = "classpath:/test.db.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
class AppUserDaoImplTest {
    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private AppUserRoleDao appUserRoleDao;

    @Test
    void save() {
        AppUser appUser = AppUser.builder()
                .appUserInfo(AppUserInfo.builder()
                        .firstname("Alexandr")
                        .lastname("Alexandrov")
                        .patronymic("Alexandrovich")
                        .email("Alex@gmail.com")
                        .build())
                .build();
        Optional<AppUserRole> maybeRole = appUserRoleDao.findRoleByName("Administrator");
        assertTrue(maybeRole.isPresent());
        maybeRole.ifPresent(appUser::setRole);
        Optional<AppUser> actualResult = appUserDao.findById(appUserDao.save(appUser).getId());
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(result -> assertAll(
                () -> assertEquals(appUser.getId(), result.getId()),
                () -> assertEquals(appUser.getAppUserInfo().getFirstname(), result.getAppUserInfo().getFirstname()),
                () -> assertEquals(appUser.getAppUserInfo().getLastname(), result.getAppUserInfo().getLastname()),
                () -> assertEquals(appUser.getAppUserInfo().getPatronymic(), result.getAppUserInfo().getPatronymic()),
                () -> assertEquals(appUser.getRole().getName(), result.getRole().getName())
        ));
    }

    @Test
    @DatabaseSetup(value = "classpath:db/users.xml")
    @DatabaseTearDown(value = "classpath:db/users.xml", type = DatabaseOperation.DELETE_ALL)
    void findAll() {
        int expectedCount = 5;
        List<AppUser> resultList = appUserDao.findAll();
        assertEquals(expectedCount, resultList.size());
    }

}
