package com.passion.fmbg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/** Board Game Atlas list of category ids
* https://api.boardgameatlas.com/api/game/categories?pretty=true&client_id=a5PDFkKaa5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories implements Serializable {
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
