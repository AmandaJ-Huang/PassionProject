package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonRootName(value = "games")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Games implements Serializable {

    @Id
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "name")
    private String name;
    private Integer year_published;
    private Integer min_players;
    private Integer max_players;
    private Integer min_playtime;
    private Integer max_playtime;
    private Integer min_age;
    private String description_preview;
    private String thumb_url;
    private String url;

    @Transient
    private Mechanics mechanics;

    @Transient
    private Categories categories;

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

    public Integer getYear_published() {
        return year_published;
    }

    public void setYear_published(Integer year_published) {
        this.year_published = year_published;
    }

    public Integer getMin_players() {
        return min_players;
    }

    public void setMin_players(Integer min_players) {
        this.min_players = min_players;
    }

    public Integer getMax_players() {
        return max_players;
    }

    public void setMax_players(Integer max_players) {
        this.max_players = max_players;
    }

    public Integer getMin_playtime() {
        return min_playtime;
    }

    public void setMin_playtime(Integer min_playtime) {
        this.min_playtime = min_playtime;
    }

    public Integer getMax_playtime() {
        return max_playtime;
    }

    public void setMax_playtime(Integer max_playtime) {
        this.max_playtime = max_playtime;
    }

    public Integer getMin_age() {
        return min_age;
    }

    public void setMin_age(Integer min_age) {
        this.min_age = min_age;
    }

    public String getDescription_preview() {
        return description_preview;
    }

    public void setDescription_preview(String description_preview) {
        this.description_preview = description_preview;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Mechanics getMechanics() {
        return mechanics;
    }

    public void setMechanics(Mechanics mechanics) {
        this.mechanics = mechanics;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
