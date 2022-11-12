package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.domain.WebsiteUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<WebsiteUser, Integer> {
    Optional<WebsiteUser> findByLogin(String login);

    WebsiteUser findFirstByLogin(String login);

}
