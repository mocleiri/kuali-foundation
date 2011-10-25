package org.kuali.maven.plugins.dnsme.accounts;

import org.kuali.maven.plugins.dnsme.Environment;
import org.kuali.maven.plugins.dnsme.beans.Account;

public class SandboxAccount extends Account {
    public SandboxAccount() {
        super();
        setUsername("kuali");
        setApiKey("549de3da-8eae-4350-b20e-3d5c31f7117e");
        setSecretKey("58dca3a2-650e-4919-bcfb-d6e6e61f4fc2");
        setEnvironment(Environment.SANDBOX);
    }
}
