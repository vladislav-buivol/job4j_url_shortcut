package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.domain.Website;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebsiteRepository extends CrudRepository<Website, Integer> {
    List<Website> findAll();

    Optional<Website> findBySite(String site);
}
