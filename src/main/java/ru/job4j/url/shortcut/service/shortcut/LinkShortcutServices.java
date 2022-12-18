package ru.job4j.url.shortcut.service.shortcut;

import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.domain.LinkShortcut;
import ru.job4j.url.shortcut.repository.LinkShortcutRepository;

import java.util.List;

@Service
public class LinkShortcutServices implements ShortcutServices {
    private final LinkShortcutRepository linkShortcutRepository;

    public LinkShortcutServices(
            LinkShortcutRepository linkShortcutRepository) {
        this.linkShortcutRepository = linkShortcutRepository;
    }

    public List<LinkShortcut> findAll() {
        return linkShortcutRepository.findAll();
    }

    public LinkShortcut save(LinkShortcut shortcut) {
        linkShortcutRepository.save(shortcut);
        return shortcut;
    }

    public LinkShortcut findFirstByCode(String code) {
        return linkShortcutRepository.findFirstByCode(code);
    }

    public void increaseCallsByOne(String code) {
        linkShortcutRepository.increaseCallsByOne(code);
    }
}
