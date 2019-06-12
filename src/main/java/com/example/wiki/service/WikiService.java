package com.example.wiki.service;

import com.example.wiki.model.WikiPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WikiService {

    @Value("${api}")
    private String wiki;

    private final RestTemplate restTemplate;

    public WikiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WikiPayload getWikiPayload(String query) throws UnsupportedEncodingException {
        var url = String.format(wiki, URLEncoder.encode(query, StandardCharsets.UTF_8.toString()));

        return restTemplate.getForObject(url, WikiPayload.class);
    }
}
