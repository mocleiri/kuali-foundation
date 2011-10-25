package org.kuali.maven.plugins.dnsme;

public enum Environment {
    PRODUCTION(Constants.PRODUCTION_URL), SANDBOX(Constants.SANDBOX_URL);

    private String url;

    private Environment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
