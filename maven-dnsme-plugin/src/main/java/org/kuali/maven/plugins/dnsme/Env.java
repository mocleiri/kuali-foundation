package org.kuali.maven.plugins.dnsme;

public enum Env {
    PRODUCTION(Constants.PRODUCTION_URL), SANDBOX(Constants.SANDBOX_URL);

    private String url;

    private Env(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
