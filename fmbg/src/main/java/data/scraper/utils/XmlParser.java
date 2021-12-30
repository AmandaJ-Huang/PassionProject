package data.scraper.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.scraper.entities.Items;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class XmlParser {
    private String rootURL = "https://www.boardgamegeek.com/xmlapi2/thing?id=0";
    private String statsURI = "&stats=1";
    private String gameId;

    public XmlParser() {
        this("1");
    }

    public XmlParser(String gameId) {
        this.gameId = gameId;
    }

    public Items parser() {
        try {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            ObjectMapper mapper = new XmlMapper(module);
            URL url = new URL(rootURL + gameId + statsURI);
            InputStream gameXml = url.openStream();

            return mapper.readValue(gameXml, Items.class);

        } catch (IOException e){
            e.printStackTrace();
        }

        return new Items();

    }

}
