package org.kuali.maven.plugins.dnsme.accounts;

import org.kuali.maven.plugins.dnsme.beans.Account;

/**
 * Config described in the DNSME docs at http://cp.dnsmadeeasy.com/enterprisedns/api.html
 */
public class SampleAccount extends Account {
    public SampleAccount() {
        super();
        setApiKey("1c1a3c91-4770-4ce7-96f4-54c0eb0e457a");
        setSecretKey("c9b5625f-9834-4ff8-baba-4ed5f32cae55");
    }
}
