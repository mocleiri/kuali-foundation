package org.kuali.maven.plugins.dnsme;

public class DNSMEClient {

    String url;

    public static DNSMEClient getInstance(Environment env) {
        return new DNSMEClient(env.getUrl());
    }

    private DNSMEClient(String url) {
        super();
        this.url = url;
    }

}
