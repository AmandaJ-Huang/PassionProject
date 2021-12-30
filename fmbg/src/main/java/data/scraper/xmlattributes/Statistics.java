package data.scraper.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "statistics")
public class Statistics {
    @JacksonXmlProperty(isAttribute = true)
    private String page;
    @JacksonXmlProperty
    private Ratings ratings;

    public Ratings getRatings() {
        return ratings;
    }
}
