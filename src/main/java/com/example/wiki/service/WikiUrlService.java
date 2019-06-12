package com.example.wiki.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Service
public class WikiUrlService {

    @Value("${result}")
    private String result;

    private final WikiService wikiService;

    public WikiUrlService(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    public String getArticleUrl(String query) throws UnsupportedEncodingException {
        var payload = wikiService.getWikiPayload(query);

        if (Objects.nonNull(payload) && Objects.nonNull(payload.getQuery()) && Objects.nonNull(payload.getQuery().getPages())) {
            return payload.getQuery().getPages().stream()
                    .filter(page -> page.getSnippet().toLowerCase().contains("football") &&
                            page.getTitle().contains(query))
                    .findFirst()
                    .map(page -> String.format(result, page.getTitle())).orElse(null);
        }
        return null;
    }
}
