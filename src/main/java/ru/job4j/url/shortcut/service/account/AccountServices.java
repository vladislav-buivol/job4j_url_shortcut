package ru.job4j.url.shortcut.service.account;

import ru.job4j.url.shortcut.domain.Account;

public interface AccountServices {
    Account createRandomUser();

    Account findFirstByLogin(String login);

    Account getCurrentAccount();
}
