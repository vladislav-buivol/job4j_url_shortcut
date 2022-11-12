package ru.job4j.url.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.domain.Website;
import ru.job4j.url.shortcut.domain.WebsiteUser;
import ru.job4j.url.shortcut.dto.request.RegistrationRequest;
import ru.job4j.url.shortcut.dto.response.ConvertResponse;
import ru.job4j.url.shortcut.dto.response.RegistrationResponse;
import ru.job4j.url.shortcut.dto.response.Response;
import ru.job4j.url.shortcut.marker.Operation;
import ru.job4j.url.shortcut.service.UserServices;
import ru.job4j.url.shortcut.service.WebsiteServices;

import java.util.Optional;

@RestController
@RequestMapping("/website")
public class WebsiteController {
    private final WebsiteServices websiteServices;
    private final UserServices userServices;
    private final BCryptPasswordEncoder encoder;

    public WebsiteController(WebsiteServices websiteServices,
                             UserServices userServices,
                             BCryptPasswordEncoder encoder) {
        this.websiteServices = websiteServices;
        this.userServices = userServices;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<Response> registration(
            @Validated(Operation.OnCreate.class) @RequestBody RegistrationRequest request) {
        Optional<Website> websiteOptional = websiteServices.findBySite(request.getSite());
        if (websiteOptional.isPresent()) {
            return new ResponseEntity<>(new RegistrationResponse(websiteOptional.get().getUser()),
                    HttpStatus.OK);
        }
        WebsiteUser user = userServices.createRandomUser();
        ResponseEntity<Response> response =
                new ResponseEntity<>(new RegistrationResponse(true, user),
                        HttpStatus.OK);

        user.encodePwd(encoder);
        Website website = new Website(request.getSite(), user);
        websiteServices.save(website);
        return response;
    }

    @PostMapping("convert")
    public ResponseEntity<Response> convert() {
        return new ResponseEntity<>(new ConvertResponse("234"), HttpStatus.OK);
    }

}
