package org.kuali.maven.plugins.dnsme;

import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.accounts.ProductionAccount;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;

public class DNSMEClientTest {

    @Test
    public void testGetDomains() throws GeneralSecurityException {
        Account account = new ProductionAccount();
        DNSMEClient client = DNSMEClient.getInstance(account);
        List<Domain> domains = client.getDomains();
        for (Domain domain : domains) {
            System.out.println(domain.getName());
        }
    }
}
