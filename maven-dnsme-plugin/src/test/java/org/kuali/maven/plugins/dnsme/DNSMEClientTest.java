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
