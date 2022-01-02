package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.Games;
import com.passion.fmbg.entities.GamesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GamesController {

    @Value("${rest.api.boardgame.url}")
    private String bgApiUrl;

    @Value("${rest.api.boardgame.client-id}")
    private String client_id;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/finder")
    public List<Games> findGames(String categories, String mechanics, Integer min_players, Integer max_players,
                                 Integer min_playtime, Integer max_playtime, Integer min_age, String order_by,
                                 String ascending) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("categories", categories);
        uriVariables.put("mechanics", mechanics);
        uriVariables.put("min_players", String.valueOf(min_players));
        uriVariables.put("max_players", String.valueOf(max_players));
        uriVariables.put("min_playtime", String.valueOf(min_playtime));
        uriVariables.put("max_playtime", String.valueOf(max_playtime));
        uriVariables.put("min_age", String.valueOf(min_age));
        uriVariables.put("order_by", order_by);
        uriVariables.put("ascending", ascending);

        ResponseEntity<GamesList> response = restTemplate
                .exchange(urlConstruct(), HttpMethod.GET, requestEntity, GamesList.class, uriVariables);

        return response.getBody().getGames();
    }

    public String urlConstruct() {

        return bgApiUrl +
                "?categories={categories}" +
                "&mechanics={mechanics}" +
                "&min_players={min_players}" +
                "&max_players={max_players}" +
                "&min_playtime={min_playtime}" +
                "&min_age={min_age}" +
                "&order_by={order_by}" +
                "&ascending={ascending}" +
                "&pretty=true" +
                "&client_id=" +
                client_id;
    }
}
