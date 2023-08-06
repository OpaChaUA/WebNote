package webnotes.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import webnotes.model.entity.User;
import webnotes.model.security.UserValidator;
import webnotes.model.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {
    @Mock
    private User user;
    @Mock
    private UserService userService;
    @Mock
    private UserValidator userValidator;
    @InjectMocks
    private RegisterController testInstance;

    @Test
    void shouldReturnRegisterPageWhenInvoked() {
        ModelAndView modelAndView = testInstance.getRegisterPage();

        assertEquals("register", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToLoginPageWhenUserIsValid() {
        ModelAndView modelAndView = testInstance.registerUser(user);

        assertEquals("redirect:/login", modelAndView.getViewName());
    }

    @Test
    void shouldShowRegisterPageWithErrorWhenUserIsNotValid() {
        when(userValidator.isUserValid(user)).thenReturn(List.of("error"));

        ModelAndView modelAndView = testInstance.registerUser(user);

        assertEquals("register", modelAndView.getViewName());
    }
}