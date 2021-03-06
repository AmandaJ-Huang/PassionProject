package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamesList {

    @JsonProperty("games")
    private List<Games> games = new ArrayList<>();

    public List<Games> getGames() {
        return games;
    }
}
