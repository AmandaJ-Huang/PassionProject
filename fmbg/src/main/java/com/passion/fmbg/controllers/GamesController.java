package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.Games;
import com.passion.fmbg.entities.GamesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GamesController {

    @Value("${rest.api.boardgame.url}")
    private String bgApiUrl;

    @Value("${rest.api.boardgame.client-id}")
    private String client_id;

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, String> uriVariables;


    @GetMapping(value = "/finder")
    public List<Games> findGames(String categories, String mechanics, Integer min_players, Integer max_players,
                                 Integer min_playtime, Integer max_playtime, Integer min_age, String order_by,
                                 String ascending) {

        try {
            uriVariables = new HashMap<>();
            uriVariables.put("categories", categories);
            uriVariables.put("mechanics", mechanics);
            uriVariables.put("min_players", String.valueOf(min_players));
            uriVariables.put("max_players", String.valueOf(max_players));
            uriVariables.put("min_playtime", String.valueOf(min_playtime));
            uriVariables.put("max_playtime", String.valueOf(max_playtime));
            uriVariables.put("min_age", String.valueOf(min_age));
            uriVariables.put("order_by", order_by);
            uriVariables.put("ascending", ascending);
            uriVariables.entrySet()
                    .removeIf(entry -> entry.getValue() == null);

            String input = "";

            for (String s : uriVariables.keySet()) {
                if (uriVariables.get(s) != null) {
                    input += (s + "={" + s + "}&");
                }
            }

            String url = bgApiUrl + "?" + input + "client_id=" + client_id;

            HttpHeaders headers = new HttpHeaders();
            HttpEntity requestEntity = new HttpEntity<>(headers);
            restTemplate = new RestTemplate();

            ResponseEntity<GamesList> response = restTemplate
                    .exchange(url, HttpMethod.GET,
                            requestEntity, GamesList.class, uriVariables);

            return response.getBody().getGames();

        } catch (Exception e) {
            List<Games> gameList = new ArrayList<>();
            e.printStackTrace();
            return gameList;
        }
    }

    public String urlConstruct() {
        String input = "";

        for (String s : uriVariables.keySet()) {
            if (uriVariables.get(s) != null) {
                input += (s + "={" + s + "}&");
            }
        }

        return bgApiUrl + "?" + input + "client_id=" + client_id;
    }
}
