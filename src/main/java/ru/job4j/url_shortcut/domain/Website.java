package ru.job4j.url_shortcut.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String site;

    private String login;

    private String password;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Website)) {
            return false;
        }
        Website website = (Website) o;
        return id == website.id &&
                Objects.equals(site, website.site) &&
                Objects.equals(login, website.login) &&
                Objects.equals(password, website.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, login, password);
    }
}
