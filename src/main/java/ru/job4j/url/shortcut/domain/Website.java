package ru.job4j.url.shortcut.domain;

import ru.job4j.url.shortcut.marker.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "website")
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null")
    @Min(value = 1, message = "Id must be greater than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @Column(unique = true)
    @NotBlank(message = "Site cannot be empty")
    private String site;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @NotNull(message = "User must be not null")
    private Account user;

    public Website() {
    }

    public Website(String site, Account user) {
        this.site = site;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Website setId(int id) {
        this.id = id;
        return this;
    }

    public String getSite() {
        return site;
    }

    public Website setSite(String site) {
        this.site = site;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public Website setUser(Account user) {
        this.user = user;
        return this;
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
        return id == website.id
                && Objects.equals(site, website.site)
                && Objects.equals(user, website.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, user);
    }
}
