package ru.job4j.url.shortcut.sanity.controller;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import ru.job4j.url.shortcut.dto.request.ConvertRequest;
import ru.job4j.url.shortcut.dto.request.RegistrationRequest;
import ru.job4j.url.shortcut.dto.request.Request;
import ru.job4j.url.shortcut.dto.response.ConvertResponse;
import ru.job4j.url.shortcut.dto.response.RegistrationResponse;
import ru.job4j.url.shortcut.dto.response.Response;
import ru.job4j.url.shortcut.dto.response.StatisticResponse;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LinkControllerSanityTest {
    @LocalServerPort
    private int port;

    public String API_REGISTRATION;
    public String API_LOGIN;
    public String API_STATISTIC;
    public String API_CONVERT;
    public String API_REDIRECT;
    public Gson gson;


    @Autowired
    private TestRestTemplate restTemplate;

    private String token;

    @BeforeEach
    public void initFields() {
        API_REGISTRATION = String.format("http://localhost:%s/website/registration", port);
        API_LOGIN = String.format("http://localhost:%s/login", port);
        API_STATISTIC = String.format("http://localhost:%S/link/statistic", port);
        API_CONVERT = String.format("http://localhost:%s/link/convert", port);
        API_REDIRECT = String.format("http://localhost:%s/link/redirect/", port);
        gson = new Gson();
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setSite("test.test");
        RegistrationResponse regResp = registerSite(registrationRequest);
        try {
            token = login(regResp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSiteInitiallyRegisteredThenTrue() {
        String site = "https://test.reg";
        RegistrationResponse response = registerSite(site);
        assertThat(response.isRegistration()).isTrue();
        response = registerSite(site);
        assertThat(response.isRegistration()).isFalse();
    }

    @Test
    public void whenRedirectedThenStatisticsChange() {
        int callsNr1 = 17;
        String site1 = "https://test.test";

        int callsNr2 = 1;
        String site2 = "https://test.test2";

        registerAndCallSite(site1, callsNr1);
        registerAndCallSite(site2, callsNr2);

        ResponseEntity<StatisticResponse> statisticResponse =
                sendRequestWithToken(API_STATISTIC, StatisticResponse.class, HttpMethod.GET);

        StatisticResponse statistic = statisticResponse.getBody();

        assertThat(statistic.getStatistic().size()).isEqualTo(2);
        assertThat(statistic.findByUrl(site1).getTotal()).isEqualTo(callsNr1);
        assertThat(statistic.findByUrl(site2).getTotal()).isEqualTo(callsNr2);
    }

    private void registerAndCallSite(String site, int callsNr) {
        registerSite(site);
        ConvertRequest convertRequest = new ConvertRequest()
                .setUrl(site);
        ConvertResponse convertResponse =
                sendRequestWithToken(API_CONVERT, ConvertResponse.class, HttpMethod.POST,
                        convertRequest).getBody();
        String shortCut = convertResponse.getCode();

        for (int i = 0; i < callsNr; i++) {
            sendRequestWithToken(API_REDIRECT + "/" + shortCut, Response.class, HttpMethod.GET);
        }
    }

    private RegistrationResponse registerSite(String site) {
        RegistrationRequest registrationRequest = new RegistrationRequest()
                .setSite(site);
        return sendRequest(API_REGISTRATION, registrationRequest, RegistrationResponse.class);
    }

    private RegistrationResponse registerSite(RegistrationRequest registrationRequest) {
        return sendRequest(API_REGISTRATION, registrationRequest, RegistrationResponse.class);
    }

    private <T extends Response> ResponseEntity<T> sendRequestWithToken(String url,
                                                                        Class<T> expectedResponse,
                                                                        HttpMethod method,
                                                                        Request request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        HttpEntity<Request> entity;
        if (request != null) {
            String json = gson.toJson(request);
            entity = new HttpEntity<Request>(request, headers);
        } else {
            entity = new HttpEntity<Request>(headers);
        }

        return restTemplate
                .exchange(url, method, entity, expectedResponse);
    }

    private <T extends Response> ResponseEntity<T> sendRequestWithToken(String url,
                                                                        Class<T> expectedResponse,
                                                                        HttpMethod method) {
        return sendRequestWithToken(url, expectedResponse, method, null);
    }

    private <T extends Response> T sendRequest(String url, Request request,
                                               Class<T> expectedResponse) {
        return restTemplate.postForObject(url, request, expectedResponse);
    }


    private String login(RegistrationResponse regResp) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("login", regResp.getLogin());
        request.put("password", regResp.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> loginResponse = restTemplate
                .exchange(API_LOGIN, HttpMethod.POST, entity, String.class);

        return Objects.requireNonNull(loginResponse.getHeaders().get("Authorization")).get(0);
    }

}
