package com.passion.fmbg.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Ratings {
    @JacksonXmlProperty
    private BoardgameElement usersrated;
    @JacksonXmlProperty
    private BoardgameElement average;
    @JacksonXmlProperty
    private BoardgameElement averageweight;

    //Ignored for database purposes
    @JacksonXmlProperty(isAttribute = true)
    private String bayesaverage;
    @JacksonXmlProperty
    private String ranks;
    @JacksonXmlProperty(isAttribute = true)
    private String rank;
    @JacksonXmlProperty(isAttribute = true)
    private String stddev;
    @JacksonXmlProperty(isAttribute = true)
    private String median;
    @JacksonXmlProperty(isAttribute = true)
    private String owned;
    @JacksonXmlProperty(isAttribute = true)
    private String trading;
    @JacksonXmlProperty(isAttribute = true)
    private String wanting;
    @JacksonXmlProperty(isAttribute = true)
    private String wishing;
    @JacksonXmlProperty(isAttribute = true)
    private String numcomments;
    @JacksonXmlProperty(isAttribute = true)
    private String numweights;


    //getters
    public BoardgameElement getUsersrated() {
        return usersrated;
    }

    public BoardgameElement getAverage() {
        return average;
    }

    public BoardgameElement getAverageweight() {
        return averageweight;
    }
}
