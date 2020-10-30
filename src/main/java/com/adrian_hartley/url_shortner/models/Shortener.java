package com.adrian_hartley.url_shortner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shortener")
public class Shortener {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long shortid;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String alias;

    @Column(nullable = false)
    @URL(message = "Not a valid URL")
    private String url;

    private long clicks = 0;

    @OneToMany(mappedBy = "shortener", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "shortener", allowSetters = true)
    private List<ShortenerMetric> metrics = new ArrayList<>();

    public Shortener(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public Shortener() {
    }

    public void setShortid(long id) {
        this.shortid = id;
    }

    public List<ShortenerMetric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<ShortenerMetric> shortenerMetrics) {
        this.metrics = shortenerMetrics;
    }

    public long getClicks() {
        return clicks;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }

    public long getShortid() {
        return shortid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
