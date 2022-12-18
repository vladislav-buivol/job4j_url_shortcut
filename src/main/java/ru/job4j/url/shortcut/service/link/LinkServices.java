package ru.job4j.url.shortcut.service.link;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.shortcut.domain.Account;
import ru.job4j.url.shortcut.domain.Link;
import ru.job4j.url.shortcut.domain.LinkShortcut;
import ru.job4j.url.shortcut.dto.entity.link.statistic.SiteStatistic;
import ru.job4j.url.shortcut.repository.LinkRepository;
import ru.job4j.url.shortcut.service.account.AccountServices;
import ru.job4j.url.shortcut.service.shortcut.LinkShortcutServices;
import ru.job4j.url.shortcut.service.shortcut.ShortcutServices;
import ru.job4j.url.shortcut.util.RandomStringGenerator;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:link.properties")
public class LinkServices {
    private final boolean multipleShortcutsPerLink;
    private final LinkRepository linkRepository;
    private final AccountServices accountServices;
    private final ShortcutServices linkShortcutServices;

    public LinkServices(
            @Value(value = "${multiple_shortcuts_per_link}") boolean multipleShortcutsPerLink,
            LinkRepository linkRepository,
            AccountServices accountServices,
            LinkShortcutServices linkShortcutServices
    ) {
        this.multipleShortcutsPerLink = multipleShortcutsPerLink;
        this.linkRepository = linkRepository;
        this.accountServices = accountServices;
        this.linkShortcutServices = linkShortcutServices;
    }

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    Optional<Link> findFirstByUrl(String link) {
        return linkRepository.findFirstByUrl(link);
    }

    public Link save(Link link) {
        linkRepository.save(link);
        return link;
    }

    public void delete(Link link) {
        linkRepository.delete(link);
    }

    public List<SiteStatistic> getStatistic() {
        return linkRepository.getStatistic();
    }

    public String boundUrlWithShortcut(String url) {
        if (!isValidUrl(url)) {
            throw new IllegalArgumentException("Invalid url: " + url);
        }
        Optional<Link> linkOpt = findFirstByUrl(url);
        String urlShortcut;
        if (linkOpt.isEmpty()) {
            urlShortcut = createShortCutFor(url);
        } else if (multipleShortcutsPerLink) {
            urlShortcut = addShortcutTo(linkOpt.get());
        } else {
            urlShortcut = linkOpt.get().getShortcuts().iterator().next().getCode();
        }
        return urlShortcut;
    }

    public void increaseCallsByOne(String code) {
        linkShortcutServices.increaseCallsByOne(code);
    }

    public String getLinkByShortcut(String code) {
        LinkShortcut shortcut = linkShortcutServices.findFirstByCode(code);
        if (shortcut == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Url not found. Please check url shortcut");
        }
        return shortcut.getLink().getUrl();
    }

    private String addShortcutTo(Link link) {
        String urlShortcut = RandomStringGenerator.generateRandomString(c -> false, 6);
        LinkShortcut shortcut = new LinkShortcut(link, urlShortcut);
        linkShortcutServices.save(shortcut);
        return urlShortcut;
    }

    private String createShortCutFor(String url) {
        Account account = accountServices.getCurrentAccount();
        String urlShortcut = RandomStringGenerator.generateRandomString(c -> false, 6);
        Link link = new Link(account, url);
        LinkShortcut shortcut = new LinkShortcut(link, urlShortcut);
        link.addShortcut(shortcut);
        save(link);
        return urlShortcut;
    }

    public boolean isValidUrl(String url) {
        return url.startsWith("https://") || url.startsWith("http://");
    }
}
