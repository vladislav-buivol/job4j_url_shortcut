package ru.job4j.url.shortcut.smoke.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.job4j.url.shortcut.service.account.WebAccountServices;
import ru.job4j.url.shortcut.service.link.LinkServices;
import ru.job4j.url.shortcut.service.website.WebsiteServices;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SmokeServiceTest {
    @Autowired
    private WebAccountServices webAccountServices;

    @Autowired
    private LinkServices linkServices;

    @Autowired
    private WebsiteServices websiteServices;

    @Test
    void whenApplicationRunThenWebAccountServicesCreated() {
        assertThat(webAccountServices).isNotNull();
    }

    @Test
    void whenApplicationRunThenLinkServicesCreated() {
        assertThat(linkServices).isNotNull();
    }

    @Test
    void whenApplicationRunThenWebsiteServicesCreated() {
        assertThat(websiteServices).isNotNull();
    }


}
