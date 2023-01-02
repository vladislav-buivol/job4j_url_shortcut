package ru.job4j.url.shortcut.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.shortcut.dto.request.ConvertRequest;
import ru.job4j.url.shortcut.dto.response.ConvertResponse;
import ru.job4j.url.shortcut.dto.response.Response;
import ru.job4j.url.shortcut.dto.response.StatisticResponse;
import ru.job4j.url.shortcut.service.link.LinkServices;

@Api
@RestController
@RequestMapping("/link")
public class LinkController {

    private final LinkServices linkServices;

    public LinkController(LinkServices linkServices) {
        this.linkServices = linkServices;
    }

    @ApiOperation("Convert url to shortcut")
    @PostMapping("/convert")
    public ResponseEntity<Response> convert(@RequestBody ConvertRequest request) {
        try {
            return new ResponseEntity<>(
                    new ConvertResponse(linkServices.boundUrlWithShortcut(request.getUrl())),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @ApiOperation("Returns redirect response (302) with redirected URL in the header")
    @GetMapping("/redirect/{shortcut}")
    public ResponseEntity<Void> redirect(@PathVariable String shortcut) {
        try {
            String link = linkServices.getLinkByShortcut(shortcut);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("REDIRECT URL", link);
            linkServices.increaseCallsByOne(shortcut);
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        } catch (Exception e) {
            throw e;
        }
    }

    @ApiOperation("Returns statistics the number of calls for all URL's")
    @GetMapping("/statistic")
    public ResponseEntity<Response> statistic() {
        try {
            return new ResponseEntity<>(new StatisticResponse(linkServices.getStatistic()),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

}
