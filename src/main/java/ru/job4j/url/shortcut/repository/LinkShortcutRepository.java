package ru.job4j.url.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.domain.LinkShortcut;

import javax.transaction.Transactional;
import java.util.List;

public interface LinkShortcutRepository extends CrudRepository<LinkShortcut, Integer> {
    List<LinkShortcut> findAll();

    LinkShortcut findFirstByCode(String code);

    @Modifying
    @Transactional
    @Query("update LinkShortcut set calls = calls + 1 where code = :code")
    void increaseCallsByOne(String code);
}
