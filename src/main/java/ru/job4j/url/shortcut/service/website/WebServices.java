package ru.job4j.url.shortcut.service.website;

import ru.job4j.url.shortcut.domain.Website;

import java.util.Collection;
import java.util.Optional;

public interface WebServices {
    Collection<Website> findAll();

    Optional<Website> findById(int id);

    Website save(Website website);

    void delete(Website website);

    Optional<Website> findBySite(String site);
}
