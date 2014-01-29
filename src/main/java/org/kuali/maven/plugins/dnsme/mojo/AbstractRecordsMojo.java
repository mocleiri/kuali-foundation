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
package org.kuali.maven.plugins.dnsme.mojo;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.GTDLocation;
import org.kuali.maven.plugins.dnsme.beans.Record;
import org.kuali.maven.plugins.dnsme.beans.RecordNameComparator;
import org.kuali.maven.plugins.dnsme.beans.RecordType;
import org.kuali.maven.plugins.dnsme.beans.Search;

/**
 * 
 */
public abstract class AbstractRecordsMojo extends BaseDNSMEMojo {
	/**
	 * The domain to show records for
	 * 
	 * @parameter expression="${dnsme.domainName}"
	 * @required
	 */
	String domainName;

	/**
	 * DEFAULT, US_EAST, US_WEST, ASIA
	 * 
	 * @parameter expression="${dnsme.gtdLocation}"
	 */
	GTDLocation gtdLocation;

	/**
	 * A, CNAME, MX, NS, PTR, SRV, AAAA, HTTPRED, TXT
	 * 
	 * @parameter expression="${dnsme.recordType}"
	 */
	RecordType recordType;

	/**
	 * Matches a single record with this exact name
	 * 
	 * @parameter expression="${dnsme.recordName}"
	 */
	String recordName;

	/**
	 * Matches any record with a name that contains this value
	 * 
	 * @parameter expression="${dnsme.recordNameContains}"
	 */
	String recordNameContains;

	/**
	 * Matches any record with this exact value
	 * 
	 * @parameter expression="${dnsme.recordValue}"
	 */
	String recordValue;

	/**
	 * Matches any record with a value that contains this value
	 * 
	 * @parameter expression="${dnsme.recordValueContains}"
	 */
	String recordValueContains;

	@Override
	public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
		Search search = new Search();
		search.setGtdLocation(gtdLocation);
		search.setType(recordType);
		search.setName(recordName);
		search.setNameContains(recordNameContains);
		search.setValue(recordValue);
		search.setValueContains(recordValueContains);

		Domain domain = client.getDomain(domainName);
		List<Record> records = client.getRecords(domain, search);

		Collections.sort(records, new RecordNameComparator());

		doRecords(records);
	}

	protected abstract void doRecords(List<Record> records);

}
