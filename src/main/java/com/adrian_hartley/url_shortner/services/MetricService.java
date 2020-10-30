package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.models.Metric;

public interface MetricService {
    Metric save(Metric metric);
    Metric findByDate(String date);
}
