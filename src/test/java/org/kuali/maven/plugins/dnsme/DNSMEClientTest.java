/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.dnsme;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.dnsme.accounts.SandboxAccount;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.Record;
import org.kuali.maven.plugins.dnsme.beans.RecordType;

public class DNSMEClientTest {

	@Test
	public void testRecordCrud() {
		try {
			Account account = new SandboxAccount();
			String restApiUrl = Constants.SANDBOX_URL;
			DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
			Domain domain = new Domain("deletemenow7.com");
			client.addDomain(domain);
			sleep(1000);
			List<Record> records = client.getRecords(domain);
			sleep(1000);
			Record record = new Record();
			record.setName("foo.bar");
			record.setTtl(60);
			record.setData("foo.aws.amazon.com");
			record.setType(RecordType.CNAME);
			Assert.assertFalse(exists(records, record));
			Record addedRecord = client.addRecord(domain, record);
			addedRecord.setTtl(600);
			sleep(1000);
			client.updateRecord(domain, addedRecord);
			sleep(1000);
			Record retrievedRecord1 = client.getRecord(domain, addedRecord.getId());
			sleep(1000);
			client.deleteRecord(domain, retrievedRecord1.getId());
			sleep(1000);
			try {
				client.getRecord(domain, addedRecord.getId());
				Assert.fail("Should have thrown an exception");
			} catch (DNSMEException e) {
				; // Expected
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// @Test
	public void testDomainCrud() {
		Account account = new SandboxAccount();
		String restApiUrl = Constants.SANDBOX_URL;
		DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);

		String newName = "deletemenow.com";
		Domain newDomain = new Domain();
		newDomain.setName(newName);

		// Make sure the domain does not already exist
		List<Domain> domains = client.getDomains();
		Assert.assertFalse(exists(domains, newDomain));

		// Add it, and make sure we can find it
		Domain resultDomain = client.addDomain(newDomain);
		domains = client.getDomains();
		Assert.assertTrue(exists(domains, resultDomain));

		sleep(5000);

		// Delete it, and make sure it is gone
		client.deleteDomain(resultDomain);

		sleep(5000);

		domains = client.getDomains();
		Assert.assertFalse(exists(domains, newDomain));
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected boolean exists(List<Record> records, Record targetRecord) {
		String targetName = targetRecord.getName();
		for (Record record : records) {
			String name = record.getName();
			if (targetName.equals(name)) {
				return true;
			}
		}
		return false;
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
