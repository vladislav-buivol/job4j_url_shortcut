package ru.job4j.url.shortcut.smoke.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.job4j.url.shortcut.controller.LinkController;
import ru.job4j.url.shortcut.controller.WebsiteController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SmokeControllerTest {
    @Autowired
    private LinkController linkController;

    @Autowired
    private WebsiteController websiteController;

    @Test
    void whenApplicationRunThenLinkControllerCreated() {
        assertThat(linkController).isNotNull();
    }

    @Test
    void whenApplicationRunThenWebsiteControllerCreated() {
        assertThat(websiteController).isNotNull();
    }
}
