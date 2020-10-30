package com.adrian_hartley.url_shortner.controllers;

import com.adrian_hartley.url_shortner.exceptions.ResourceFoundException;
import com.adrian_hartley.url_shortner.exceptions.ResourceNotFoundException;
import com.adrian_hartley.url_shortner.models.Shortener;
import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import com.adrian_hartley.url_shortner.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RedirectController {

    @Autowired
    ShortenerService shortenerService;

    /**
     * *
     * @param alias the ID of the URL
     * @return 301 Moved Permanently -- Redirects to desired URL
     */
    @GetMapping("/{alias}")
    @Transactional
    public ResponseEntity<?> handleRedirect(@PathVariable String alias, HttpServletRequest request) throws URISyntaxException {
        Shortener shortener = shortenerService.getRedirect(alias)
                .orElseThrow(() -> new ResourceNotFoundException("Alias Does Not Exist."));

        System.out.println(request.getRemoteAddr());

        shortenerService.update(shortener, request.getRemoteAddr());

        URI uri = new URI(shortener.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * Get the metrics of a specified link
     * @param alias the ID of the URL
     * @return 200 OK -- The link with it's metrics
     */
    @GetMapping("/metrics/{alias}")
    @Transactional
    public ResponseEntity<?> getMetrics(@PathVariable String alias) {
        Shortener shortener = shortenerService.getMetrics(alias);

        return new ResponseEntity<>(shortener, HttpStatus.OK);
    }

    /**
     * Create a new shortened url
     * @return JSON object containing the shortened URL
     */
    @Transactional
    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreation redirectCreation) throws URISyntaxException {
        Shortener shortener = shortenerService.createRedirect(redirectCreation)
                .orElseThrow(() -> new ResourceFoundException("Alias already exists."));

        URI uri = new URI(shortener.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(shortener, httpHeaders, HttpStatus.CREATED);
    }
}
