package com.passion.fmbg.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.passion.fmbg.entities.Games;
import com.passion.fmbg.entities.GamesList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class GamesController {

    @GetMapping(value = "/boardgames")
    public GamesList getGames() throws IOException {
        String url = "https://api.boardgameatlas.com/api/search?ids=TAAifFP590&client_id=a5PDFkKaa5";
        RestTemplate template = new RestTemplate();

        return template.getForObject(url, GamesList.class);
    }
}
