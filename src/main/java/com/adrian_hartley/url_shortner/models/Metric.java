package com.adrian_hartley.url_shortner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metric")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long metricid;

    private String date;

    private String ip;

    @OneToMany(mappedBy = "metric", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "metric", allowSetters = true)
    private List<ShortenerMetric> metrics = new ArrayList<>();

    public Metric() {
    }

    public Metric(String date, String ip) {
        this.date = date;
        this.ip = ip;
    }

    public List<ShortenerMetric> getMetrics() {
        return metrics;
    }

    public Long getMetricid() {
        return metricid;
    }

    public void setMetrics(List<ShortenerMetric> shortenerMetrics) {
        this.metrics = shortenerMetrics;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
