package it.academy.dao;

import it.academy.DataConfig;
import it.academy.model.AppUserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Transactional
@TestPropertySource(value = "classpath:/test.db.properties")
class AppUserRoleDaoImplTest {
    @Autowired
    private AppUserRoleDao appUserRoleDao;

    @Test
    public void findAppUserRoleByName() {
        String expectedRole = "Administrator";
        Optional<AppUserRole> actualResult = appUserRoleDao.findRoleByName(expectedRole);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(role -> assertEquals(expectedRole, role.getName()));
    }
}
