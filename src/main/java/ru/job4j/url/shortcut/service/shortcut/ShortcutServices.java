package ru.job4j.url.shortcut.service.shortcut;

import ru.job4j.url.shortcut.domain.LinkShortcut;

public interface ShortcutServices {
    LinkShortcut save(LinkShortcut shortcut);

    LinkShortcut findFirstByCode(String code);

    void increaseCallsByOne(String code);
}
