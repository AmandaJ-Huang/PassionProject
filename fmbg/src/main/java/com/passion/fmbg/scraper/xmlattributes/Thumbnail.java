package com.passion.fmbg.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Thumbnail {
    @JacksonXmlText
    private String thumbnailUrl;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
