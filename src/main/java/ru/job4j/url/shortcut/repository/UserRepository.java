package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.domain.Account;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByLogin(String login);

    Account findFirstByLogin(String login);

}
