package data.scraper.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "item")
public class Item {
    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    private BoardgameElement thumbnail;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private List<Name> nameList;

    @JacksonXmlProperty(isAttribute = true, localName = "yearpublished")
    private BoardgameElement yearPublished;

    @JacksonXmlProperty(isAttribute = true, localName = "minplayers")
    private BoardgameElement minPlayers;

    @JacksonXmlProperty(isAttribute = true, localName = "maxplayers")
    private BoardgameElement maxPlayers;

    @JacksonXmlProperty(isAttribute = true, localName = "playingtime")
    private BoardgameElement playingTime;

    @JacksonXmlProperty(isAttribute = true, localName = "minplaytime")
    private BoardgameElement minPlaytime;

    @JacksonXmlProperty(isAttribute = true, localName = "maxplaytime")
    private BoardgameElement maxPlaytime;

    @JacksonXmlProperty(isAttribute = true, localName = "minage")
    private BoardgameElement minAge;

    @JacksonXmlProperty(isAttribute = true, localName = "link")
    private List<Link> linkList;

    @JacksonXmlProperty(isAttribute = true, localName = "usersrated")
    private BoardgameElement usersRated;

    @JacksonXmlProperty(isAttribute = true)
    private BoardgameElement average;

    @JacksonXmlProperty(isAttribute = true)
    private Statistics statistics;

    @JacksonXmlProperty(isAttribute = true, localName = "averageweight")
    private BoardgameElement averageWeight;

    // Necessary values for parsing XML properly, but ignored for purposes of database
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(isAttribute = true)
    private String image;
    @JacksonXmlProperty(isAttribute = true)
    private String description;
    @JacksonXmlProperty(isAttribute = true)
    private String poll;
    @JacksonXmlProperty(isAttribute = true)
    private String results;
    @JacksonXmlProperty(isAttribute = true)
    private String result;

    // Getters
    public String getType() {
        return type;
    }

    public BoardgameElement getThumbnail() {
        return thumbnail;
    }

    public List<Name> getNameList() {
        return nameList;
    }

    public BoardgameElement getYearpublished() {
        return yearPublished;
    }

    public BoardgameElement getMinPlayers() {
        return minPlayers;
    }

    public BoardgameElement getMaxPlayers() {
        return maxPlayers;
    }

    public BoardgameElement getPlayingTime() {
        return playingTime;
    }

    public BoardgameElement getMinPlaytime() {
        return minPlaytime;
    }

    public BoardgameElement getMaxPlaytime() {
        return maxPlaytime;
    }

    public BoardgameElement getMinAge() {
        return minAge;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public BoardgameElement getUsersRated() {
        return statistics.getRatings().getUsersrated();
    }

    public BoardgameElement getAverage() {
        return statistics.getRatings().getAverage();
    }

    public BoardgameElement getAverageWeight() {
        return statistics.getRatings().getAverageweight();
    }

}
