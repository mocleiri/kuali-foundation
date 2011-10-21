package org.kuali.maven.plugins;

public class SandboxConfig implements Config {
    String apiKey = "454f2836-81c0-4379-b8dd-2cc6495131b5";
    String secretKey = "5fc3245f-78b5-4c92-9053-be0b5c64a680";
    String baseUrl = "http://api.dnsmadeeasy.com/V1.2/domains/";

    @Override
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
