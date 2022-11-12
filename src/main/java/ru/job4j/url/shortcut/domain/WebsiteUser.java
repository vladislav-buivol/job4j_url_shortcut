package ru.job4j.url.shortcut.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.url.shortcut.marker.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class WebsiteUser {
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

    public WebsiteUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public WebsiteUser() {
    }

    public String getLogin() {
        return login;
    }

    public WebsiteUser setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public WebsiteUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public void encodePwd(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WebsiteUser)) {
            return false;
        }
        WebsiteUser user = (WebsiteUser) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
