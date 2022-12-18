package ru.job4j.url.shortcut.service.account;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.domain.Account;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final WebAccountServices accountServices;

    public UserDetailsServiceImpl(WebAccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountServices.findFirstByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }
}
