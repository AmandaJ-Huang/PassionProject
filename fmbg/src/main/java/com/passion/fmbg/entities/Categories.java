package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@JsonIgnoreProperties
public class Categories {

    @Id
    @JsonProperty
    private String id;

    private String url;
}
