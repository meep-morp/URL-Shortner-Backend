package com.adrian_hartley.url_shortner.services;

import com.adrian_hartley.url_shortner.exceptions.ResourceFoundException;
import com.adrian_hartley.url_shortner.exceptions.ResourceNotFoundException;
import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.repositories.ShortenerRepo;
import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Optional<Shortener> createRedirect(RedirectCreation redirectCreation) {
        if(shortenerRepo.existsByAlias(redirectCreation.getAlias())) {
            throw new ResourceFoundException("Alias Already Exists.");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Shortener> getRedirect(RedirectCreation redirectCreation) {
        return Optional.empty();
    }
}
