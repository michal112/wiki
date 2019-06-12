package com.example.wiki.controller;

import com.example.wiki.service.WikiUrlService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.io.UnsupportedEncodingException;

@RestController
@Validated
public class WikiController {

    private final WikiUrlService wikiUrlService;

    public WikiController(WikiUrlService wikiUrlService) {
        this.wikiUrlService = wikiUrlService;
    }

    @GetMapping("/wiki/{query}")
    public String getRepositoryInfo(@NotEmpty @PathVariable("query") String query) throws UnsupportedEncodingException {
        return wikiUrlService.getArticleUrl(query);
    }
}
