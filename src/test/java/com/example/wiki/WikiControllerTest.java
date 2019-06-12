package com.example.wiki;

import com.example.wiki.controller.WikiController;
import com.example.wiki.model.WikiPayload;
import com.example.wiki.service.WikiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WikiController.class, includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.wiki.service.*"))
@TestPropertySource(properties = "result=https://en.wikipedia.org/wiki/Liverpool")
public class WikiControllerTest {

    private final static String LIVERPOOL_WIKI_ANSWER = "{\"batchcomplete\":\"\",\"continue\":{\"sroffset\":10,\"continue\":\"-||\"},\"query\":{\"searchinfo\":{\"totalhits\":59992},\"search\":[{\"ns\":0,\"title\":\"Liverpool F.C.\",\"pageid\":18119,\"size\":101917,\"wordcount\":8113,\"snippet\":\"<span class=\\\"searchmatch\\\">Liverpool</span> Football Club is a professional football club in <span class=\\\"searchmatch\\\">Liverpool</span>, England, that competes in the Premier League, the top tier of English football. The\",\"timestamp\":\"2019-06-11T02:21:16Z\"},{\"ns\":0,\"title\":\"Liverpool\",\"pageid\":18081,\"size\":211876,\"wordcount\":19901,\"snippet\":\"<span class=\\\"searchmatch\\\">Liverpool</span> is a city and metropolitan borough in North West England, with an estimated population of 491,500. Its metropolitan area is the fifth-largest\",\"timestamp\":\"2019-06-12T14:00:56Z\"},{\"ns\":0,\"title\":\"2019 UEFA Champions League Final\",\"pageid\":53077927,\"size\":87798,\"wordcount\":6629,\"snippet\":\"English sides Tottenham Hotspur, in their first European Cup final, and <span class=\\\"searchmatch\\\">Liverpool</span>, in their ninth final overall and their second in a row, having been defeated\",\"timestamp\":\"2019-06-12T14:56:23Z\"},{\"ns\":0,\"title\":\"Jordan Henderson\",\"pageid\":20027350,\"size\":43163,\"wordcount\":3499,\"snippet\":\"is an English professional footballer who captains Premier League club <span class=\\\"searchmatch\\\">Liverpool</span> and plays for the England national team. He is usually deployed as a central\",\"timestamp\":\"2019-06-11T12:25:43Z\"},{\"ns\":0,\"title\":\"Mohamed Salah\",\"pageid\":35440786,\"size\":152876,\"wordcount\":13051,\"snippet\":\"professional footballer who plays as a forward for Premier League club <span class=\\\"searchmatch\\\">Liverpool</span> and the Egypt national team. Considered one of the best players in the\",\"timestamp\":\"2019-06-04T20:49:35Z\"},{\"ns\":0,\"title\":\"2005 UEFA Champions League Final\",\"pageid\":4861876,\"size\":51837,\"wordcount\":4443,\"snippet\":\"was contested between <span class=\\\"searchmatch\\\">Liverpool</span> of England and Milan of Italy at the Atat\u00fcrk Stadium in Istanbul, Turkey on 25 May 2005. <span class=\\\"searchmatch\\\">Liverpool</span>, who had won the competition\",\"timestamp\":\"2019-05-17T21:01:45Z\"},{\"ns\":0,\"title\":\"J\u00fcrgen Klopp\",\"pageid\":17541051,\"size\":102694,\"wordcount\":8874,\"snippet\":\"football manager and former player who is the manager of Premier League club <span class=\\\"searchmatch\\\">Liverpool</span>. Often credited with popularising the football philosophy known as Gegenpressing\",\"timestamp\":\"2019-06-11T08:37:06Z\"},{\"ns\":0,\"title\":\"Virgil van Dijk\",\"pageid\":35611957,\"size\":51773,\"wordcount\":4669,\"snippet\":\"footballer who plays as a centre-back for English Premier League club <span class=\\\"searchmatch\\\">Liverpool</span> and captains the Netherlands national team. After beginning his professional\",\"timestamp\":\"2019-06-11T02:08:25Z\"},{\"ns\":0,\"title\":\"Steven Gerrard\",\"pageid\":547384,\"size\":128925,\"wordcount\":11244,\"snippet\":\"spent the majority of his playing career as a central midfielder for <span class=\\\"searchmatch\\\">Liverpool</span>, with most of that time spent as club captain, as well as captaining the\",\"timestamp\":\"2019-05-19T15:53:16Z\"},{\"ns\":0,\"title\":\"Anfield\",\"pageid\":178253,\"size\":56776,\"wordcount\":5548,\"snippet\":\"Anfield is a football stadium in Anfield, <span class=\\\"searchmatch\\\">Liverpool</span>, England, which has a seating capacity of 54,074, making it the seventh largest football stadium in\",\"timestamp\":\"2019-06-10T20:54:44Z\"}]}}";

    @Value("${result}")
    private String result;

    @MockBean
    private WikiService wikiService;

    @Autowired
    private WikiController wikiController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnLiverpoolUrl() throws Exception {
        given(wikiService.getWikiPayload("Liverpool")).willReturn(objectMapper.readValue(LIVERPOOL_WIKI_ANSWER, WikiPayload.class));

        mockMvc.perform(get("/wiki/Liverpool"))
                .andExpect(content().string(result));
    }
}
