package webnotes.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webnotes.model.entity.User;
import webnotes.model.enums.Role;
import webnotes.model.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_USER.name());
        user.setEnabled(1);
        return userRepository.save(user);
    }

    public int getIdByUsername(String username) {
        return  userRepository.findByUsername(username).getId();
    }

    public boolean isUserExist(String username) {
        return Objects.nonNull(userRepository.findByUsername(username));
    }
}
