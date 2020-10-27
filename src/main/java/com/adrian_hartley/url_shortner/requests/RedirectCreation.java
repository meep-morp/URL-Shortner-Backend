package com.adrian_hartley.url_shortner.requests;

import com.sun.istack.NotNull;

public class RedirectCreation {
    @NotNull
    private final String alias;

    @NotNull
    private final String url;

    public RedirectCreation(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }
}
