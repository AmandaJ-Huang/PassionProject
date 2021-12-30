package data.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Link {
    @JacksonXmlProperty
    private String type;

    @JacksonXmlProperty
    private String id;

    @JacksonXmlProperty
    private String value;

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
