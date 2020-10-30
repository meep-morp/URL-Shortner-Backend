package com.adrian_hartley.url_shortner;

import com.adrian_hartley.url_shortner.models.Shortener;
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

        shortener.getDates().add("30-07-2020");
        shortener.getDates().add("01-10-2020");
        shortener.getDates().add("10-10-2020");
        shortener.getDates().add("30-10-2020");

        shortener.getIpaddresses().add("47.13.216.8");
        shortener.getIpaddresses().add("89.39.197.226");
        shortener.getIpaddresses().add("249.14.171.202");
        shortener.getIpaddresses().add("235.96.46.165");

        shortener.setClicks(4);

        shortenerService.save(shortener);
    }
}

