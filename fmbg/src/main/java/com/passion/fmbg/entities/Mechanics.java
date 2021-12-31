package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "mechanics")
@JsonIgnoreProperties
public class Mechanics {

    @Id
    @JsonProperty
    private String id;

    private String url;

}
