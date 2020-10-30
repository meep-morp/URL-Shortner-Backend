package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.repositories.MetricRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "metricService")
public class MetricServiceImpl implements MetricService{
    @Autowired
    MetricRepo metricRepo;

    @Override
    public Metric save(Metric metric) {
        return metricRepo.save(metric);
    }

    @Override
    public Metric findByDate(String date) {
        return metricRepo.findByDate(date);
    }
}
