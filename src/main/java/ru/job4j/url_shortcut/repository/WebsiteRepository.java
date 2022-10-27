package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.domain.Website;

import java.util.List;

public interface WebsiteRepository extends CrudRepository<Website, Integer> {

    @Override
    List<Website> findAll();
}
