package com.adrian_hartley.url_shortner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shortenermetric")
public class ShortenerMetric implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "shortid")
    @JsonIgnoreProperties(value = "metrics", allowSetters = true)
    private Shortener shortener;

    @Id
    @ManyToOne
    @JoinColumn(name = "metricid")
    @JsonIgnoreProperties(value = "metrics", allowSetters = true)
    private Metric metric;

    public ShortenerMetric() {
    }

    public ShortenerMetric(Shortener shortener, Metric metric) {
        this.shortener = shortener;
        this.metric = metric;
    }

    public Shortener getShortener() {
        return shortener;
    }

    public void setShortener(Shortener shortener) {
        this.shortener = shortener;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
