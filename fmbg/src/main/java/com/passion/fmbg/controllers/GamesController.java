package com.passion.fmbg.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.passion.fmbg.entities.Games;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/boardgames")
    public Games getGames() throws IOException {
        URL url = new URL("https://api.boardgameatlas.com/api/search?ids=TAAifFP590&client_id=a5PDFkKaa5");
        ObjectMapper mapper = new ObjectMapper();
        mapper.canDeserialize(mapper.constructType(Games.class));
        Games game = mapper.readValue(url, Games.class);
        logger.info(game.toString());
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return game;
    }
}
