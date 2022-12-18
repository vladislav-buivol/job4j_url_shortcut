package ru.job4j.url.shortcut.dto.request;

import javax.validation.constraints.NotBlank;

public class ConvertRequest implements Request {
    @NotBlank(message = "Url must not be empty")
    private String url;

    public String getUrl() {
        return url;
    }

    public ConvertRequest setUrl(String url) {
        this.url = url;
        return this;
    }
}
