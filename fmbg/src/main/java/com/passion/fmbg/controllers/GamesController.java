package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.GamesList;
import com.passion.fmbg.specs.GameSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/findmegames")
public class GamesController {
    private String client_id = "&client_id=a5PDFkKaa5";

    @Value("${api.host.url}")
    private String bgApi;

    @GetMapping(value = "&{gameSpec}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GamesList getGames(GameSpec gameSpec) {
        RestTemplate template = new RestTemplate();

        return template.getForObject(bgApi + gameSpec + client_id, GamesList.class);
    }
}
