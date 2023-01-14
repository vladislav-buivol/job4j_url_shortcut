package ru.job4j.url.shortcut.domain;

import ru.job4j.url.shortcut.marker.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null")
    @Min(value = 1, message = "Id must be greater than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @Column(unique = true)
    @NotNull(message = "Login must be not null")
    private String login;

    @Column(unique = true)
    @NotNull(message = "Password must be not null")
    private String password;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Account() {
    }

    public String getLogin() {
        return login;
    }

    public Account setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account user = (Account) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
