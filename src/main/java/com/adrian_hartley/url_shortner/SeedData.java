package com.adrian_hartley.url_shortner;

import com.adrian_hartley.url_shortner.models.Metric;
import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.models.ShortenerMetric;
import com.adrian_hartley.url_shortner.services.MetricService;
import com.adrian_hartley.url_shortner.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private ShortenerService shortenerService;

    @Autowired
    private MetricService metricService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Shortener shortener = new Shortener("example", "https://meep-morp.com/");
        Metric m4 = new Metric("30-10-2020", "47.13.216.8");
        Metric m3 = new Metric("10-10-2020", "89.39.197.226");
        Metric m2 = new Metric("01-10-2020", "249.14.171.202");
        Metric m1 = new Metric("30-07-2020", "235.96.46.165");

        metricService.save(m1);
        metricService.save(m2);
        metricService.save(m3);
        metricService.save(m4);

        shortener.setClicks(14);

        shortener.getMetrics().add(new ShortenerMetric(shortener, m1));
        shortener.getMetrics().add(new ShortenerMetric(shortener, m2));
        shortener.getMetrics().add(new ShortenerMetric(shortener, m3));
        shortener.getMetrics().add(new ShortenerMetric(shortener, m4));

        shortenerService.save(shortener);
    }
}

