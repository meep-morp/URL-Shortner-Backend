package com.adrian_hartley.url_shortner.repositories;

import com.adrian_hartley.url_shortner.models.Metric;
import org.springframework.data.repository.CrudRepository;

public interface MetricRepo extends CrudRepository<Metric, Long> {
    Metric findByDate(String date);
    Boolean existsByDate(String date);
}
