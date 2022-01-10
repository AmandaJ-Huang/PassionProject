package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/** Board Game Atlas list of mechanic ids
 * https://api.boardgameatlas.com/api/game/mechanics?pretty=true&client_id=a5PDFkKaa5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mechanics implements Serializable {
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
