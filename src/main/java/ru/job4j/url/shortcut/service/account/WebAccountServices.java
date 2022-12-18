package ru.job4j.url.shortcut.service.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.domain.Account;
import ru.job4j.url.shortcut.repository.UserRepository;

import static ru.job4j.url.shortcut.util.RandomStringGenerator.generateRandomString;

@Service
@PropertySource("classpath:user.properties")
public class WebAccountServices implements AccountServices {
    private final UserRepository userRepository;

    @Value(value = "${login_min_length}")
    private int loginMinLength;

    @Value(value = "${password_min_length}")
    private int passwordMinLength;

    public WebAccountServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Account createRandomUser() {
        String login = generateRandomString(l -> userRepository.findByLogin(l).isPresent(),
                loginMinLength);
        String password = generateRandomString(p -> false, passwordMinLength);
        return new Account(login, password);
    }

    public Account findFirstByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public Account getCurrentAccount() {
        return findFirstByLogin(((String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()));
    }
}
