package org.kuali.maven.plugins.dnsme;

import com.google.gson.Gson;

public class DNSMEClient {

    String url;
    Gson gson = new Gson();
    HttpUtil http = new HttpUtil();
    DNSMEUtil dnsme = new DNSMEUtil();

    public static DNSMEClient getInstance(Environment env) {
        return new DNSMEClient(env.getUrl());
    }

    private DNSMEClient(String url) {
        super();
        this.url = url;
    }

    public List<Domain> getDomains() {
        String url = this.url + "/domains";
        Method method = dnsme.get 
        
    }
}
