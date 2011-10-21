package org.kuali.maven.plugins.dnsme.config;

public class SandboxConfig extends DefaultConfig {
    public SandboxConfig() {
        super();
        setApiKey("549de3da-8eae-4350-b20e-3d5c31f7117e");
        setSecretKey("58dca3a2-650e-4919-bcfb-d6e6e61f4fc2");
        setBaseUrl("http://api.sandbox.dnsmadeeasy.com/V1.2/domains/kuali.org/records/");
    }
}
