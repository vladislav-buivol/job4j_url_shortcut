package ru.job4j.url_shortcut.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.service.WebsiteServices;

@RestController
@RequestMapping("/website")
public class WebsiteController {
    private final WebsiteServices websiteServices;

    public WebsiteController(WebsiteServices websiteServices) {
        this.websiteServices = websiteServices;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
