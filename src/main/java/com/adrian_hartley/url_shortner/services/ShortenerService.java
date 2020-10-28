package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ShortenerService {
    Shortener findByAlias(String alias);
    Boolean existsByAlias(String alias);
    Optional<Shortener> createRedirect(RedirectCreation redirectCreation);
    Optional<Shortener> getRedirect(String alias);
    Shortener save(Shortener shortener);
    Shortener update(Shortener shortener);
    Shortener getMetrics(String alias);
}
