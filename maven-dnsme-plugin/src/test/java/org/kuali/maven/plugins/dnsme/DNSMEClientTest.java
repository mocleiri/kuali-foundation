package org.kuali.maven.plugins.dnsme;

import java.util.List;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.accounts.SandboxAccount;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;

public class DNSMEClientTest {

    @Test
    public void testGetDomains() {
        try {
            Account account = new SandboxAccount();
            String restApiUrl = Constants.SANDBOX_URL;
            DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
            List<Domain> domains = client.getDomains();
            for (Domain domain : domains) {
                System.out.println(domain.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
