package com.adrian_hartley.url_shortner.controllers;

import com.adrian_hartley.url_shortner.requests.RedirectCreation;
import com.adrian_hartley.url_shortner.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
        URI uri = new URI("http://www.google.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    /**
     * *
     * @return JSON object containing the shortened URL
     */
    @PostMapping("/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectCreation redirectCreation) {
        return null;
    }
}
