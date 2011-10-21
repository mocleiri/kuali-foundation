package org.kuali.maven.plugins.dnsme.config;

/**
 * Config described in the DNSME docs at http://cp.dnsmadeeasy.com/enterprisedns/api.html
 */
public class SampleConfig extends DefaultConfig {
    public SampleConfig() {
        super();
        setApiKey("1c1a3c91-4770-4ce7-96f4-54c0eb0e457a");
        setSecretKey("c9b5625f-9834-4ff8-baba-4ed5f32cae55");
        setBaseUrl("http://api.sandbox.dnsmadeeasy.com/V1.2/domains/");
    }
}
