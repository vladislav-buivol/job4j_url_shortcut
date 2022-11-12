package ru.job4j.url.shortcut.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.domain.WebsiteUser;
import ru.job4j.url.shortcut.repository.UserRepository;

import static ru.job4j.url.shortcut.util.RandomStringGenerator.generateRandomString;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Value(value = "${login.min.length}")
    private int loginMinLength;

    @Value(value = "${password.min.length}")
    private int passwordMinLength;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public WebsiteUser createRandomUser() {
        String login = generateRandomString(l -> userRepository.findByLogin(l).isPresent(),
                loginMinLength);
        String password = generateRandomString(p -> false, passwordMinLength);
        return new WebsiteUser(login, password);
    }


    public WebsiteUser findFirstByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }
}
