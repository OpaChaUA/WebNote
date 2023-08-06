package webnotes.model.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import webnotes.model.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService testInstance;

    @Test
    @Rollback
    void shouldAddUserWhenInvoked() {
        User user = User.builder()
                .username("user1")
                .password("12345678")
                .build();

        User addedUser = testInstance.add(user);

        assertTrue(addedUser.getId() > 2);
    }

    @Test
    @Rollback
    void shouldReturnIdByUsernameWhenInvoked() {
        int adminId = testInstance.getIdByUsername("admin");

        assertEquals(1, adminId);
    }

    @Test
    @Rollback
    void shouldCheckIfUserExistsWhenInvoke() {
        boolean result = testInstance.isUserExist("admin");

        assertTrue(result);
    }
}