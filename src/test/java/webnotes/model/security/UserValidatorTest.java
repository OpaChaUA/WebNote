package webnotes.model.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import webnotes.model.entity.User;
import webnotes.model.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {
    @Mock
    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserValidator testInstance;

    @BeforeEach
    void setUp() {
        when(user.getUsername()).thenReturn("user1");
        when(user.getPassword()).thenReturn("12345678");
    }

    @Test
    void shouldReturnErrorWhenUserIsValidAndExists() {
        when(userService.isUserExist("user1")).thenReturn(true);

        List<String> errorMessageList = testInstance.isUserValid(user);

        assertTrue(errorMessageList.contains("User already exists"));
    }

    @Test
    void shouldReturnErrorsWhenUserIsNotValid() {
        when(user.getUsername()).thenReturn("user");
        when(user.getPassword()).thenReturn("1234567");

        List<String> errorMessageList = testInstance.isUserValid(user);

        assertEquals(2, errorMessageList.size());
    }

    @Test
    void shouldNotReturnErrorsWhenUserIsValidAndNotExist() {
        when(userService.isUserExist("user1")).thenReturn(false);

        List<String> errorMessageList = testInstance.isUserValid(user);

        assertTrue(errorMessageList.isEmpty());
    }
}