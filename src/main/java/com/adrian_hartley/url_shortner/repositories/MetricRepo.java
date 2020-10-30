package com.adrian_hartley.url_shortner.repositories;

import org.springframework.data.repository.CrudRepository;

public interface MetricRepo extends CrudRepository<Metric, Long> {
    Metric findByDate(String date);
    Boolean existsByDate(String date);
}
