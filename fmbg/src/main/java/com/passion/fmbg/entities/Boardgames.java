package com.passion.fmbg.entities;

import javax.persistence.*;

@Entity
@Table(name = "bg", schema = "boardgames")
public class Boardgames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bg_id")
    private Long bgId;

    private String thumbnail;

    @Column(name = "gameurl")
    private String gameUrl;

    @Column(name = "primaryname")
    private String primaryName;

    @Column(name = "yearpublished")
    private Integer yearPublished;

    @Column(name = "minplayers")
    private Integer minPlayers;

    @Column(name = "maxplayers")
    private Integer maxPlayers;

    @Column(name = "playingtime")
    private Integer playingTime;

    @Column(name = "minplaytime")
    private Integer minPlaytime;

    @Column(name = "maxplaytime")
    private Integer maxPlaytime;

    @Column(name = "minage")
    private Integer minAge;

    private String category;

    @Column(name = "user_ratings")
    private Long userRatings;

    @Column(name = "average_rating")
    private Float averageRating;
    private Float difficulty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBgId() {
        return bgId;
    }

    public void setBgId(Long bgId) {
        this.bgId = bgId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(Integer playingTime) {
        this.playingTime = playingTime;
    }

    public Integer getMinPlaytime() {
        return minPlaytime;
    }

    public void setMinPlaytime(Integer minPlaytime) {
        this.minPlaytime = minPlaytime;
    }

    public Integer getMaxPlaytime() {
        return maxPlaytime;
    }

    public void setMaxPlaytime(Integer maxPlaytime) {
        this.maxPlaytime = maxPlaytime;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(Long userRatings) {
        this.userRatings = userRatings;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Float difficulty) {
        this.difficulty = difficulty;
    }
}
