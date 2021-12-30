package com.passion.fmbg.entities;

import javax.persistence.*;

@Entity
@Table(name = "bg", schema = "boardgames")
public class Boardgames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thumbnail;
    private String gameUrl;
    private String primaryName;
    private String yearPublished;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer playingTime;
    private Integer minPlaytime;
    private Integer maxPlaytime;
    private Integer minAge;
    private String category;
    private Long userRatings;
    private Float averageRating;
    private Float difficulty;
}
