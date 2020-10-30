package com.adrian_hartley.url_shortner.services;

public interface MetricService {
    Metric save(Metric metric);
    Metric findByDate(String date);
}
