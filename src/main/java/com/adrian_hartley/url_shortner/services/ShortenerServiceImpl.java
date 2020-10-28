package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.exceptions.ResourceFoundException;
import com.adrian_hartley.url_shortner.exceptions.ResourceNotFoundException;
import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.repositories.ShortenerRepo;
import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("shortenerService")
public class ShortenerServiceImpl implements ShortenerService {
    @Autowired
    ShortenerRepo shortenerRepo;

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
        return shortenerRepo.save(shortener);
    }

    @Override
    @Transactional
    public Shortener update(Shortener shortener) {
        Shortener currentShortener = shortenerRepo.findById(shortener.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));

        if(shortener.getUrl() != null) {
            currentShortener.setUrl(shortener.getUrl());
        }
        if(shortener.getAlias() != null) {
            currentShortener.setAlias(shortener.getAlias());
        }

        currentShortener.setClicks(shortener.getClicks() + 1);

        return shortenerRepo.save(currentShortener);
    }

    @Override
    public Shortener getMetrics(String alias) {
        return shortenerRepo.findByAlias(alias)
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));
    }
}
