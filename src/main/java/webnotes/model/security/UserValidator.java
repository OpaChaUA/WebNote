package webnotes.model.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webnotes.model.entity.User;
import webnotes.model.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserService userService;
    public List<String> isUserValid(User user) {
        List<String> errorMessageList = new ArrayList<>();
        if (!user.getUsername().matches("^[A-Za-z0-9]{5,50}$")) {
            errorMessageList.add("Username must consist of latin letters or digits and be between 5 and 50 symbols");
        }
        if (!user.getPassword().matches("^\\S{8,100}$")) {
            errorMessageList.add("Password must be between 8 and 100 symbols");
        }
        if (errorMessageList.isEmpty() && userService.isUserExist(user.getUsername())) {
            errorMessageList.add("User already exists");
        }
        return errorMessageList;
    }
}
