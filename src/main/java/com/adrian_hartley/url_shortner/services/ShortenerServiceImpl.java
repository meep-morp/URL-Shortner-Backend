package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.exceptions.ResourceFoundException;
import com.adrian_hartley.url_shortner.exceptions.ResourceNotFoundException;
import com.adrian_hartley.url_shortner.models.Metric;
import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.models.ShortenerMetric;
import com.adrian_hartley.url_shortner.repositories.MetricRepo;
import com.adrian_hartley.url_shortner.repositories.ShortenerRepo;
import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service("shortenerService")
public class ShortenerServiceImpl implements ShortenerService {
    @Autowired
    ShortenerRepo shortenerRepo;

    @Autowired
    MetricRepo metricRepo;

    @Autowired
    ShortenerService shortenerService;

    @Override
    public Shortener findByAlias(String alias) {
        return shortenerRepo.findByAlias(alias)
                .orElseThrow(()-> new ResourceNotFoundException("Alias Does Not Exist."));
    }

    @Override
    public Boolean existsByAlias(String alias) {
        return shortenerRepo.existsByAlias(alias);
    }

    /**
     * Creates a new shortened link
     * @param redirectCreation The object sent in the post, contains an alias and a url
     * @return The newly made link
     */

    @Override
    public Optional<Shortener> createRedirect(RedirectCreation redirectCreation) {
        System.out.println("URL: " + redirectCreation.getUrl() + " Alias: " + redirectCreation.getAlias());
        if(shortenerRepo.existsByAlias(redirectCreation.getAlias())) {
            throw new ResourceFoundException("Alias Already Exists.");
        }
        Shortener shortener = shortenerRepo.save(new Shortener(redirectCreation.getAlias(), redirectCreation.getUrl()));
        return Optional.of(shortener);
    }

    @Override
    public Optional<Shortener> getRedirect(String alias) {
        Shortener shortener = shortenerRepo.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));

        return Optional.of(shortener);
    }

    @Transactional
    @Override
    public Shortener save(Shortener shortener) {
        Shortener newShortener = new Shortener();
        newShortener.setShortid(shortener.getShortid());

        newShortener.setClicks(shortener.getClicks());
        newShortener.setAlias(shortener.getAlias());
        newShortener.setUrl(shortener.getUrl());

        newShortener.getMetrics().clear();
        for(ShortenerMetric s : shortener.getMetrics()) {
            Metric metric = metricRepo.findById(s.getMetric().getMetricid())
                    .orElseThrow(() -> new ResourceNotFoundException("Metric Not Found"));
            newShortener.getMetrics().add(new ShortenerMetric(newShortener, metric));
        }

        return shortenerRepo.save(shortener);
    }

    @Override
    @Transactional
    public Shortener update(Shortener shortener, String ip) {

        Shortener currentShortener = shortenerRepo.findById(shortener.getShortid())
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String convertedDate = dateFormat.format(date);

        Metric metric = new Metric(convertedDate, ip);
        metricRepo.save(metric);

        if(shortener.getUrl() != null) {
            currentShortener.setUrl(shortener.getUrl());
        }

        if(shortener.getAlias() != null) {
            currentShortener.setAlias(shortener.getAlias());
        }

        currentShortener.setClicks(shortener.getClicks() + 1);

        for (ShortenerMetric s : shortener.getMetrics()) {
            currentShortener.getMetrics().add(s);
        }

        System.out.println(currentShortener.getShortid());

        currentShortener.getMetrics().add(new ShortenerMetric(currentShortener, metric));

        for(ShortenerMetric m : currentShortener.getMetrics()) {
            System.out.println("Date " + m.getMetric().getDate());
        }

        return shortenerService.save(currentShortener);
    }

    @Override
    public Shortener getMetrics(String alias) {
        return shortenerRepo.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));
    }
}
