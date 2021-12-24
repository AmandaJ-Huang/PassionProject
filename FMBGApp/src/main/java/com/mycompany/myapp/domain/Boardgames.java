package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Boardgames.
 */
@Entity
@Table(name = "boardgames")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Boardgames implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "thumbnail_url")
    private String thumbnail_url;

    @Column(name = "primary_name")
    private String primary_name;

    @Column(name = "minplayers")
    private Integer minplayers;

    @Column(name = "maxplayers")
    private Integer maxplayers;

    @Column(name = "suggested_numplayers")
    private Integer suggested_numplayers;

    @Column(name = "playingtime")
    private Integer playingtime;

    @Column(name = "suggested_playerage")
    private Integer suggested_playerage;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "jhi_rank")
    private Long rank;

    @Column(name = "averageweight")
    private Float averageweight;

    @Column(name = "category")
    private String category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Boardgames id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnail_url() {
        return this.thumbnail_url;
    }

    public Boardgames thumbnail_url(String thumbnail_url) {
        this.setThumbnail_url(thumbnail_url);
        return this;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getPrimary_name() {
        return this.primary_name;
    }

    public Boardgames primary_name(String primary_name) {
        this.setPrimary_name(primary_name);
        return this;
    }

    public void setPrimary_name(String primary_name) {
        this.primary_name = primary_name;
    }

    public Integer getMinplayers() {
        return this.minplayers;
    }

    public Boardgames minplayers(Integer minplayers) {
        this.setMinplayers(minplayers);
        return this;
    }

    public void setMinplayers(Integer minplayers) {
        this.minplayers = minplayers;
    }

    public Integer getMaxplayers() {
        return this.maxplayers;
    }

    public Boardgames maxplayers(Integer maxplayers) {
        this.setMaxplayers(maxplayers);
        return this;
    }

    public void setMaxplayers(Integer maxplayers) {
        this.maxplayers = maxplayers;
    }

    public Integer getSuggested_numplayers() {
        return this.suggested_numplayers;
    }

    public Boardgames suggested_numplayers(Integer suggested_numplayers) {
        this.setSuggested_numplayers(suggested_numplayers);
        return this;
    }

    public void setSuggested_numplayers(Integer suggested_numplayers) {
        this.suggested_numplayers = suggested_numplayers;
    }

    public Integer getPlayingtime() {
        return this.playingtime;
    }

    public Boardgames playingtime(Integer playingtime) {
        this.setPlayingtime(playingtime);
        return this;
    }

    public void setPlayingtime(Integer playingtime) {
        this.playingtime = playingtime;
    }

    public Integer getSuggested_playerage() {
        return this.suggested_playerage;
    }

    public Boardgames suggested_playerage(Integer suggested_playerage) {
        this.setSuggested_playerage(suggested_playerage);
        return this;
    }

    public void setSuggested_playerage(Integer suggested_playerage) {
        this.suggested_playerage = suggested_playerage;
    }

    public Float getRating() {
        return this.rating;
    }

    public Boardgames rating(Float rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getRank() {
        return this.rank;
    }

    public Boardgames rank(Long rank) {
        this.setRank(rank);
        return this;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Float getAverageweight() {
        return this.averageweight;
    }

    public Boardgames averageweight(Float averageweight) {
        this.setAverageweight(averageweight);
        return this;
    }

    public void setAverageweight(Float averageweight) {
        this.averageweight = averageweight;
    }

    public String getCategory() {
        return this.category;
    }

    public Boardgames category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Boardgames)) {
            return false;
        }
        return id != null && id.equals(((Boardgames) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Boardgames{" +
            "id=" + getId() +
            ", thumbnail_url='" + getThumbnail_url() + "'" +
            ", primary_name='" + getPrimary_name() + "'" +
            ", minplayers=" + getMinplayers() +
            ", maxplayers=" + getMaxplayers() +
            ", suggested_numplayers=" + getSuggested_numplayers() +
            ", playingtime=" + getPlayingtime() +
            ", suggested_playerage=" + getSuggested_playerage() +
            ", rating=" + getRating() +
            ", rank=" + getRank() +
            ", averageweight=" + getAverageweight() +
            ", category='" + getCategory() + "'" +
            "}";
    }
}
