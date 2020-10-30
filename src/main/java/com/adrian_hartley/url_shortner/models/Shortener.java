package com.adrian_hartley.url_shortner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shortener {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String alias;

    @Column(nullable = false)
    @URL(message = "Not a valid URL")
    private String url;

    private long clicks = 0;

    @ElementCollection
    private List<String> dates = new ArrayList<>();

    @ElementCollection
    private List<String> ipaddresses = new ArrayList<>();

    public Shortener(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public Shortener() {
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<String> getIpaddresses() {
        return ipaddresses;
    }

    public void setIpaddresses(List<String> ipaddresses) {
        this.ipaddresses = ipaddresses;
    }

    public long getClicks() {
        return clicks;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
