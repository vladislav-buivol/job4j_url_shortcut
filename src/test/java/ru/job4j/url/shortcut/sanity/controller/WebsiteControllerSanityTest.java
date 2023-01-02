package ru.job4j.url.shortcut.sanity.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import ru.job4j.url.shortcut.dto.request.RegistrationRequest;
import ru.job4j.url.shortcut.dto.request.Request;
import ru.job4j.url.shortcut.dto.response.RegistrationResponse;
import ru.job4j.url.shortcut.dto.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class WebsiteControllerSanityTest {
    @LocalServerPort
    private int port;

    public String API_REGISTRATION;

    @BeforeEach
    public void initFields() {
        API_REGISTRATION = String.format("http://localhost:%s/website/registration", port);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenSiteInitiallyRegisteredThenRegistrationIsTrue() {
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setSite("test.test");
        RegistrationResponse response =
                sendRequest(API_REGISTRATION, registrationRequest, RegistrationResponse.class);
        assertThat(response.isRegistration()).isTrue();
    }

    @Test
    public void whenSiteAlreadyRegisteredThenRegistrationIsFalse() {
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setSite("test.test");
        sendRequest(API_REGISTRATION, registrationRequest, RegistrationResponse.class);
        RegistrationResponse response =
                sendRequest(API_REGISTRATION, registrationRequest, RegistrationResponse.class);

        assertThat(response.isRegistration()).isFalse();
    }

    private <T extends Response> T sendRequest(String url, Request request,
                                               Class<T> expectedResponse) {
        return restTemplate.postForObject(url, request, expectedResponse);
    }
}