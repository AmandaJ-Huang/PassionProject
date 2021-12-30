package com.passion.fmbg.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "items")
public class Items {
    @JacksonXmlProperty
    private String termsofuse;
    @JacksonXmlProperty
    private Item item;

    public Item getItem() {
        return item;
    }
}
