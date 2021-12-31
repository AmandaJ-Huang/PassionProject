package datascraper.ignore.xmlattributes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Name {
    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    private String sortindex;

    @JacksonXmlProperty(isAttribute = true)
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }
}
