package ru.job4j.url.shortcut.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Account account;

    @Column(unique = true)
    @NotBlank(message = "Url cannot be empty")
    private String url;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private Set<LinkShortcut> shortcuts = new HashSet<>();

    public Link() {
    }

    public Link(Account account,
                @NotBlank(message = "Url cannot be empty") String url) {
        this.account = account;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<LinkShortcut> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(Set<LinkShortcut> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public void addShortcut(LinkShortcut shortcut) {
        getShortcuts().add(shortcut);
    }
}
