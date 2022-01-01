package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Games implements Serializable {

    private String id;
    private String url;
    private String name;
    private Integer year_published;
    private Integer min_players;
    private Integer max_players;
    private Integer min_playtime;
    private Integer max_playtime;
    private Integer min_age;
    private String description_preview;
    private String thumb_url;
    private Integer rank;

    @JsonProperty(value = "average_learning_complexity")
    private Float complexity;

    private List<Mechanics> mechanics = new ArrayList<>();
    private List<Categories> categories = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Integer getYear_published() {
        return year_published;
    }

    public Integer getMin_players() {
        return min_players;
    }

    public Integer getMax_players() {
        return max_players;
    }

    public Integer getMin_playtime() {
        return min_playtime;
    }

    public Integer getMax_playtime() {
        return max_playtime;
    }

    public Integer getMin_age() {
        return min_age;
    }

    public String getDescription_preview() {
        return description_preview;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public Integer getRank() {
        return rank;
    }

    public Float getComplexity() {
        return complexity;
    }

    public List<Mechanics> getMechanics() {
        return mechanics;
    }

    public List<Categories> getCategories() {
        return categories;
    }


}
