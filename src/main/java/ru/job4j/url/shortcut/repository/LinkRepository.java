package ru.job4j.url.shortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.domain.Link;
import ru.job4j.url.shortcut.dto.entity.link.statistic.SiteStatistic;

import java.util.List;
import java.util.Optional;
@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {
    List<Link> findAll();

    Optional<Link> findFirstByUrl(String url);

    @Query("select new ru.job4j.url.shortcut.dto.entity.link.statistic."
            + "SiteStatistic(l.url, sum(shortcut.calls)) "
            + "from LinkShortcut shortcut "
            + "join Link l on shortcut.link.id = l.id group by l.url")
    List<SiteStatistic> getStatistic();
}
