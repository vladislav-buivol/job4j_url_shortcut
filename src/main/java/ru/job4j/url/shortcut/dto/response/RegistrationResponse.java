package ru.job4j.url.shortcut.dto.response;

import ru.job4j.url.shortcut.domain.Account;

public class RegistrationResponse implements Response {
    private boolean registration = false;
    private String login;
    private String password;

    public RegistrationResponse() {
    }

    public RegistrationResponse(boolean registration, String login, String password) {
        this.registration = registration;
        this.login = login;
        this.password = password;
    }

    public RegistrationResponse(boolean registration, Account user) {
        this.registration = registration;
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public RegistrationResponse(Account user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
