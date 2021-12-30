package com.passion.fmbg.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Link {
    @JacksonXmlProperty
    private String type;

    @JacksonXmlProperty
    private String id;

    @JacksonXmlProperty
    private String value;

    @JacksonXmlProperty
    private String inbound;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
