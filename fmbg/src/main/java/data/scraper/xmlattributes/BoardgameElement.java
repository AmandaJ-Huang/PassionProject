package data.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BoardgameElement {
    @JacksonXmlProperty(isAttribute = true)
    private String value;

    public String getValue() {
        return value;
    }
}
