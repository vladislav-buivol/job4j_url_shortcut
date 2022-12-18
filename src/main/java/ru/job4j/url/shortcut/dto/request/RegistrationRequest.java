package ru.job4j.url.shortcut.dto.request;

import javax.validation.constraints.NotBlank;

public class RegistrationRequest implements Request {

    @NotBlank(message = "Site must not be empty")
    private String site;

    public String getSite() {
        return site;
    }

    public RegistrationRequest setSite(String site) {
        this.site = site;
        return this;
    }
}
