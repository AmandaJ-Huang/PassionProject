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
}
