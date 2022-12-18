package ru.job4j.url.shortcut.dto.response;

public class ConvertResponse implements Response {
    private String code;

    public ConvertResponse(String code) {
        this.code = code;
    }

    public ConvertResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
