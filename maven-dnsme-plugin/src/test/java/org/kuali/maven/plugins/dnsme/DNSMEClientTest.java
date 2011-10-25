package org.kuali.maven.plugins.dnsme;

import java.util.List;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.accounts.SandboxAccount;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.Record;

public class DNSMEClientTest {

    @Test
    public void testAddRecord() {
        try {
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
            System.out.println(resultRecord.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
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
