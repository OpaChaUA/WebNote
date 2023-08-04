package webnotes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import webnotes.model.entity.User;
import webnotes.model.security.UserValidator;
import webnotes.model.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute User user) {
        List<String> errorMessageList = userValidator.isUserValid(user);
        if (errorMessageList.isEmpty()) {
            userService.add(user);
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("register")
                .addObject("errMes", errorMessageList);
    }
}
