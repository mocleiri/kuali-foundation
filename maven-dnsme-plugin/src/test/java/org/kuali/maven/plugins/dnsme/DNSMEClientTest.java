package org.kuali.maven.plugins.dnsme;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.accounts.SandboxAccount;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.Record;

public class DNSMEClientTest {

    @Test
    public void testDomainCrud() {
        Account account = new SandboxAccount();
        String restApiUrl = Constants.SANDBOX_URL;
        DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);

        String newName = "xyzz.com";
        Domain newDomain = new Domain();
        newDomain.setName(newName);

        // Make sure the domain does not already exist
        List<Domain> domains = client.getDomains();
        Assert.assertFalse(exists(domains, newDomain));

        // Add it, and make sure we can find it
        Domain resultDomain = client.addDomain(newDomain);
        domains = client.getDomains();
        Assert.assertTrue(exists(domains, resultDomain));

        // Delete it, and make sure it is gone
        client.deleteDomain(resultDomain);
        domains = client.getDomains();
        Assert.assertFalse(exists(domains, newDomain));

    }

    protected boolean exists(List<Domain> domains, Domain targetDomain) {
        String targetName = targetDomain.getName();
        for (Domain domain : domains) {
            String name = domain.getName();
            if (targetName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    // @Test
    public void testAddRecord() {
        Account account = new SandboxAccount();
        String restApiUrl = Constants.SANDBOX_URL;
        DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
        Domain domain = new Domain();
        domain.setName("foomanchu.com");
        Record record = new Record();
        record.setName("foo.bar");
        record.setTtl(60);
        record.setData("foo.aws.amazon.com");
        record.setType(RecordType.CNAME);
        Record resultRecord = client.addRecord(domain, record);
        Assert.assertNotNull(resultRecord.getId());
    }

    // @Test
    public void testGetDomains() {
        Account account = new SandboxAccount();
        String restApiUrl = Constants.SANDBOX_URL;
        DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
        List<Domain> domains = client.getDomains();
        Assert.assertTrue(domains.size() > 0);
    }
}
