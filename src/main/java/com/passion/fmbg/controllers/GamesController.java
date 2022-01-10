package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.Games;
import com.passion.fmbg.entities.GamesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@CrossOrigin(origins = "https://fmbg.herokuapp.com")
@RestController
@RequestMapping("/api")
public class GamesController {

    @Value("${rest.api.boardgame.url}")
    private String bgApiUrl;

    @Value("${rest.api.boardgame.client-id}")
    private String client_id;

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, String> uriVariables;

    @GetMapping(value = "/finder")
    public List<Games> findGames(String categories, String mechanics, Integer gt_min_players, Integer lt_max_players,
                                 Integer gt_min_playtime, Integer lt_max_playtime, Integer gt_min_age, String order_by,
                                 String ascending) {

        try {
            uriVariables = new HashMap<>();

            if (categories != null && !categories.isEmpty()) {
                uriVariables.put("categories", categories);
            }

            if (mechanics != null && !mechanics.isEmpty()) {
                uriVariables.put("mechanics", mechanics);
            }

            if (gt_min_players != null && !String.valueOf(gt_min_players).isEmpty()) {
                uriVariables.put("gt_min_players", String.valueOf(gt_min_players-1));
            }

            if (lt_max_players != null && !String.valueOf(lt_max_players).isEmpty()) {
                uriVariables.put("lt_max_players", String.valueOf(lt_max_players+1));
            }

            if (gt_min_playtime != null && !String.valueOf(gt_min_playtime).isEmpty()) {
                uriVariables.put("gt_min_playtime", String.valueOf(gt_min_playtime-1));
            }

            if (lt_max_playtime != null && !String.valueOf(lt_max_playtime).isEmpty()) {
                uriVariables.put("lt_max_playtime", String.valueOf(lt_max_playtime));
            }

            if (gt_min_age != null && !String.valueOf(gt_min_age).isEmpty()) {
                uriVariables.put("gt_min_age", String.valueOf(gt_min_age-1));
            }

            if (order_by != null && !order_by.isEmpty()) {
                uriVariables.put("order_by", order_by);
            }

            if (ascending != null && !ascending.isEmpty()) {
                uriVariables.put("ascending", ascending);
            }

            HttpHeaders headers = new HttpHeaders();
            HttpEntity requestEntity = new HttpEntity<>(headers);
            restTemplate = new RestTemplate();

            ResponseEntity<GamesList> response = restTemplate
                    .exchange(urlConstruct(), HttpMethod.GET,
                            requestEntity, GamesList.class, uriVariables);

            return response.getBody().getGames();

        } catch (Exception e) {
            List<Games> gameList = new ArrayList<>();
            e.getMessage();
            return gameList;
        }
    }

    public String urlConstruct() {
        String input = "";

        for (String s : uriVariables.keySet()) {
                input += (s + "={" + s + "}&");
        }

        return bgApiUrl + "?" + input + "client_id=" + client_id;
    }
}
