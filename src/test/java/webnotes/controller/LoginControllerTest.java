package webnotes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {
    private final LoginController testInstance = new LoginController();

    @Test
    void shouldReturnLoginPageWhenInvoked() {
        ModelAndView modelAndView = testInstance.getLoginPage();

        assertEquals("login", modelAndView.getViewName());
    }
}