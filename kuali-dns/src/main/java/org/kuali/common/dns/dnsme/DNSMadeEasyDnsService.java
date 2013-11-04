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
package org.kuali.common.dns.dnsme;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.dnsme.model.DnsMadeEasyDnsRecord;
import org.kuali.common.dns.dnsme.model.DnsMadeEasyDnsRecordComparator;
import org.kuali.common.dns.dnsme.model.DnsMadeEasyDomain;
import org.kuali.common.dns.dnsme.model.DnsMadeEasyDomainNames;
import org.kuali.common.dns.dnsme.model.DnsMadeEasySearchCriteria;
import org.kuali.common.dns.http.HttpRequestResult;
import org.kuali.common.dns.http.HttpUtil;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.dns.model.DnsRecordType;
import org.kuali.common.dns.util.DnsUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DNSMadeEasyDnsService implements DnsService {

	public static final int HTTP_OK = 200;
	public static final int HTTP_CREATED = 201;

	// These are immutable and thus ok to expose via getters
	private final DNSMadeEasyServiceContext context;
	private final String restApiUrl;
	private final String domainName;
	private final DNSMadeEasyCredentials credentials;

	//
	private final DnsMadeEasyDomain domain;
	private final Gson gson = new Gson();
	private final HttpUtil http = new HttpUtil();
	private final DNSMEUtil dnsme = new DNSMEUtil();

	public DNSMadeEasyDnsService(DNSMadeEasyServiceContext context) {
		Assert.noNulls(context);
		Assert.isFalse(EncUtils.isEncrypted(context.getCredentials().getSecretKey()), "Secret key is encrypted");
		this.context = context;
		this.restApiUrl = context.getRestApiUrl();
		this.credentials = context.getCredentials();
		this.domainName = context.getDomainName();

		// Now that the rest of the instance variables have been initialized it is safe to invoke the getDomain() method
		// This establishes an http connection to DNSME and creates a DnsMadeEasyDomain object from the domain name string
		this.domain = getDomain(domainName);
	}

	protected List<DnsMadeEasyDomain> getDomains() {
		String url = this.restApiUrl + "/domains";
		String json = getJson(url, HTTP_OK);
		DnsMadeEasyDomainNames domainNames = gson.fromJson(json, DnsMadeEasyDomainNames.class);
		return getDomains(domainNames);
	}

	protected DnsMadeEasyDomain getDomain(String name) {
		List<DnsMadeEasyDomain> domains = getDomains();
		for (DnsMadeEasyDomain domain : domains) {
			if (domain.getName().equalsIgnoreCase(name)) {
				return domain;
			}
		}
		return null;
	}

	protected DnsMadeEasyDomain addDomain(DnsMadeEasyDomain domain) {
		String url = this.restApiUrl + "/domains/" + domain.getName();
		PutMethod method = new PutMethod(url);
		return addOrUpdateObject(url, HTTP_CREATED, domain, method);
	}

	protected void deleteDomain(DnsMadeEasyDomain domain) {
		String url = this.restApiUrl + "/domains/" + domain.getName();
		deleteObject(url);
	}

	protected String getQueryString(DnsMadeEasySearchCriteria search) {
		List<NameValuePair> pairs = getPairs(search);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pairs.size(); i++) {
			NameValuePair pair = pairs.get(i);
			if (i == 0) {
				sb.append("?");
			} else {
				sb.append("&");
			}
			sb.append(pair.getName() + "=" + encode(pair.getValue()));
		}
		return sb.toString();
	}

	protected List<NameValuePair> getPairs(DnsMadeEasySearchCriteria search) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		addIfNotNull(pairs, getPair("name", search.getName()));
		addIfNotNull(pairs, getPair("nameContains", search.getNameContains()));
		addIfNotNull(pairs, getPair("value", search.getValue()));
		addIfNotNull(pairs, getPair("valueContains", search.getValueContains()));
		addIfNotNull(pairs, getPair("gtdLocation", search.getGtdLocation()));
		addIfNotNull(pairs, getPair("type", search.getType()));
		return pairs;
	}

	protected void addIfNotNull(List<NameValuePair> pairs, NameValuePair pair) {
		if (pair == null) {
			return;
		} else {
			pairs.add(pair);
		}
	}

	protected NameValuePair getPair(String name, Object value) {
		if (value == null) {
			return null;
		} else {
			return new NameValuePair(name, value.toString());
		}
	}

	protected String encode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	protected DnsMadeEasyDnsRecord getRecord(DnsMadeEasyDomain domain, DnsMadeEasySearchCriteria search) {
		List<DnsMadeEasyDnsRecord> records = getRecords(domain, search);
		if (records.size() != 1) {
			throw new IllegalStateException("Search criteria must match exactly 1 record but it matched " + records.size() + " records");
		} else {
			return records.get(0);
		}
	}

	protected List<DnsMadeEasyDnsRecord> getRecords(DnsMadeEasyDomain domain, DnsMadeEasySearchCriteria search) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
		if (search != null) {
			url += getQueryString(search);
		}
		String json = getJson(url, HTTP_OK);
		List<DnsMadeEasyDnsRecord> records = getRecords(json);
		for (DnsMadeEasyDnsRecord record : records) {
			record.setDomain(domain);
		}
		return records;
	}

	protected DnsMadeEasySearchCriteria getSearch(String name, DnsRecordType type) {
		DnsMadeEasySearchCriteria search = new DnsMadeEasySearchCriteria();
		search.setName(name);
		search.setType(type);
		return search;
	}

	protected DnsMadeEasySearchCriteria getSearch(String name) {
		DnsMadeEasySearchCriteria search = new DnsMadeEasySearchCriteria();
		search.setName(name);
		return search;
	}

	protected DnsMadeEasyDnsRecord getRecord(DnsMadeEasyDomain domain, String name) {
		return getRecord(domain, getSearch(name));
	}

	protected DnsMadeEasyDnsRecord getRecord(DnsMadeEasyDomain domain, int recordId) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + recordId;
		String resultJson = getJson(url, HTTP_OK);
		DnsMadeEasyDnsRecord resultRecord = gson.fromJson(resultJson, DnsMadeEasyDnsRecord.class);
		return resultRecord;
	}

	protected void validateForUpdate(DnsMadeEasyDnsRecord record) {
		if (record.getId() == null && record.getName() == null) {
			throw new IllegalStateException("Either id or name must have a value when updating");
		}
	}

	protected void updateRecord(DnsMadeEasyDomain domain, DnsMadeEasyDnsRecord record) {
		validateForUpdate(record);
		if (record.getId() == null) {
			DnsMadeEasyDnsRecord existingRecord = getRecord(domain, record.getName());
			record.setId(existingRecord.getId());
		}
		validateRecord(record);
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + record.getId();
		PutMethod method = new PutMethod(url);
		addOrUpdateObject(url, HTTP_OK, record, method);
	}

	protected DnsMadeEasyDnsRecord addRecord(DnsMadeEasyDomain domain, DnsMadeEasyDnsRecord record) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
		if (record.getId() != null) {
			throw new IllegalStateException("id must be null when adding");
		}
		validateRecord(record);
		PostMethod method = new PostMethod(url);
		return addOrUpdateObject(url, HTTP_CREATED, record, method);
	}

	protected void deleteRecord(DnsMadeEasyDomain domain, int recordId) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + recordId;
		deleteObject(url);
	}

	protected void deleteRecord(DnsMadeEasyDomain domain, String name, DnsRecordType type) {
		DnsMadeEasySearchCriteria search = getSearch(name, type);
		DnsMadeEasyDnsRecord record = getRecord(domain, search);
		Assert.isTrue(record.getId() != null, "id is required");
		deleteRecord(domain, record.getId());
	}

	protected void deleteRecord(DnsMadeEasyDomain domain, String name) {
		DnsMadeEasyDnsRecord record = getRecord(domain, name);
		Assert.isTrue(record.getId() != null, "id is required");
		deleteRecord(domain, record.getId());
	}

	protected void deleteCNAMERecord(DnsMadeEasyDomain domain, String name) {
		DnsMadeEasyDnsRecord record = getRecord(domain, name);
		Assert.isTrue(record.getId() != null, "id is required");
		deleteRecord(domain, record.getId());
	}

	protected void deleteObject(String url) {
		HttpMethod method = dnsme.getDeleteMethod(credentials, url);
		HttpRequestResult result = http.executeMethod(method);
		validateResult(result, HTTP_OK);
	}

	protected List<DnsMadeEasyDnsRecord> getCNAMERecords(DnsMadeEasyDomain domain) {
		DnsMadeEasySearchCriteria search = new DnsMadeEasySearchCriteria();
		search.setType(DnsRecordType.CNAME);
		List<DnsMadeEasyDnsRecord> records = getRecords(domain, search);
		Collections.sort(records, new DnsMadeEasyDnsRecordComparator());
		return records;
	}

	protected void validateRecord(DnsMadeEasyDnsRecord record) {
		StringBuilder sb = new StringBuilder();
		if (record.getName() == null) {
			sb.append("Name must not be null\n");
		}
		if (record.getData() == null) {
			sb.append("Data must not be null\n");
		}
		if (record.getTtl() == null) {
			sb.append("TTL must not be null\n");
		}
		if (record.getType() == null) {
			sb.append("Type must not be null\n");
		}
		if (sb.length() > 0) {
			throw new IllegalStateException(sb.toString());
		}
	}

	protected <T> T addOrUpdateObject(String url, int statusCode, T object, EntityEnclosingMethod method) {
		String json = gson.toJson(object);
		dnsme.updateMethod(credentials, json, method);
		String resultJson = getJson(url, method, statusCode);
		@SuppressWarnings("unchecked")
		T resultObject = (T) gson.fromJson(resultJson, object.getClass());
		return resultObject;
	}

	protected List<DnsMadeEasyDnsRecord> getRecords(DnsMadeEasyDomain domain) {
		return getRecords(domain, null);
	}

	protected String getJson(String url, HttpMethod method, int successCode) {
		HttpRequestResult result = http.executeMethod(method);
		validateResult(result, successCode);
		return result.getResponseBody();
	}

	protected String getJson(String url, int successCode) {
		HttpMethod method = dnsme.getGetMethod(credentials, url);
		return getJson(url, method, successCode);
	}

	protected List<DnsMadeEasyDnsRecord> getRecords(String json) {
		Type recordsListType = new TypeToken<List<DnsMadeEasyDnsRecord>>() {
		}.getType();

		@SuppressWarnings("unchecked")
		List<DnsMadeEasyDnsRecord> records = (List<DnsMadeEasyDnsRecord>) gson.fromJson(json, recordsListType);
		if (records == null) {
			return new ArrayList<DnsMadeEasyDnsRecord>();
		} else {
			return records;
		}
	}

	protected void validateResult(HttpRequestResult result, int statusCode) {
		switch (result.getType()) {
		case EXCEPTION:
			throw new IllegalStateException(result.getException());
		case TIMEOUT:
			throw new IllegalStateException("Operation timed out");
		case COMPLETED:
			int code = result.getStatusCode();
			if (statusCode == result.getStatusCode()) {
				return;
			} else {
				String errorText = result.getResponseBody();
				throw new IllegalStateException("Invalid http status '" + code + ":" + result.getStatusText() + "' Expected: '" + statusCode + "'  Error:" + errorText);
			}
		default:
			throw new IllegalStateException("Unknown result type: " + result.getType());
		}
	}

	protected List<DnsMadeEasyDomain> getDomains(DnsMadeEasyDomainNames domainNames) {
		if (domainNames.getList() == null) {
			return new ArrayList<DnsMadeEasyDomain>();
		}
		List<String> names = domainNames.getList();
		List<DnsMadeEasyDomain> domains = new ArrayList<DnsMadeEasyDomain>();
		for (String name : names) {
			DnsMadeEasyDomain domain = new DnsMadeEasyDomain(credentials, name);
			domains.add(domain);
		}
		return domains;
	}

	@Override
	public String getDomainName() {
		return domainName;
	}

	/**
	 * Validate that <code>fqdn</code> ends with <code>domain</code>
	 */
	protected void validateDomain(String fqdn, String domain) {
		Assert.noBlanks(domain, fqdn);
		Assert.isTrue(fqdn.endsWith(domain), "[" + fqdn + "] doesn't end with [" + domain + "]");
	}

	protected String getRecordNameFromFQDN(String fqdn, String domain) {
		// Blanks not allowed
		Assert.noBlanks(domain, fqdn);

		// Make sure fqdn is in the domain
		validateDomain(fqdn, domain);

		// Trim the domain off the end of the fqdn
		int start = 0;
		int end = fqdn.length() - domain.length();
		String fragment = fqdn.substring(start, end);

		// Account for the root record by only trimming off a dot at the end if it is present
		if (fragment.endsWith(".")) {
			return fragment.substring(0, fragment.length() - 1);
		} else {
			return fragment;
		}
	}

	@Override
	public DnsRecord createCNAMERecord(String aliasFQDN, String fqdn, int timeToLiveInSeconds) {

		// Make sure both are syntactically valid fully qualified domain names
		DnsUtils.validateFQDN(aliasFQDN);
		DnsUtils.validateFQDN(fqdn);

		// The alias must be in our domain
		validateDomain(aliasFQDN, getDomainName());

		// TTL can't be negative
		Assert.noNegatives(timeToLiveInSeconds);

		// The dot at the end is a magic value telling DNSME that this is a fully qualified domain name
		// Without it, DNSME auto-appends our domain name
		fqdn = fqdn + ".";

		// Trim the domain name off the end of the aliasFQDN
		String recordName = getRecordNameFromFQDN(aliasFQDN, getDomainName());

		// Create a Record object
		DnsMadeEasyDnsRecord record = new DnsMadeEasyDnsRecord();
		record.setName(recordName);
		record.setType(DnsRecordType.CNAME);
		record.setData(fqdn);
		record.setTtl(timeToLiveInSeconds);

		// Actually add the record
		DnsMadeEasyDnsRecord added = addRecord(domain, record);

		// Convert to a DnsRecord and return
		return new DnsRecord(added.getName(), added.getType(), record.getData());
	}

	@Override
	public boolean isExistingCNAMERecord(String fqdn) {

		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only check for the existence of fqdn's in our domain
		validateDomain(fqdn, getDomainName());

		// Extract the DNS record name from the fqdn
		String recordName = getRecordNameFromFQDN(fqdn, getDomainName());

		// Setup a search object based on the fqdn
		DnsMadeEasySearchCriteria search = getSearch(recordName, DnsRecordType.CNAME);

		// Get a list of matching records from DNSME
		List<DnsMadeEasyDnsRecord> records = getRecords(domain, search);

		// If there is more than 1 record, something has gone wrong
		Assert.isTrue(records.size() <= 1, "Found " + records.size() + " records when expecting a max of 1");

		// Size can only be zero or one here
		// Return true if size is one
		// We found a record matching the fqdn they gave us
		return records.size() == 1;
	}

	@Override
	public void deleteCNAMERecord(String fqdn) {
		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only delete fqdn's in our domain
		validateDomain(fqdn, getDomainName());

		// If it exists, delete it
		if (isExistingCNAMERecord(fqdn)) {

			// Trim the domain name off the fqdn
			String recordName = getRecordNameFromFQDN(fqdn, domainName);

			// Delete the DNS record
			deleteRecord(domain, recordName, DnsRecordType.CNAME);
		}
	}

	@Override
	public DnsRecord getCNAMERecord(String fqdn) {
		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only get DNS records for fqdn's in our domain
		validateDomain(fqdn, getDomainName());

		// Setup a search object based on the fqdn
		DnsMadeEasySearchCriteria search = getSearch(fqdn, DnsRecordType.CNAME);

		// Get a list of matching records from DNSME
		List<DnsMadeEasyDnsRecord> records = getRecords(domain, search);

		// If there is more than 1 record, something has gone wrong
		Assert.isTrue(records.size() == 1, "Found " + records.size() + " records when expecting exactly 1");

		// Extract the first (and only) item in the list
		DnsMadeEasyDnsRecord record = records.get(0);

		// Create a new DnsRecord from the DNSME record
		return new DnsRecord(record.getName(), record.getType(), record.getData());
	}

	@Override
	public List<DnsRecord> getRecords() {
		List<DnsMadeEasyDnsRecord> records = getRecords(domain);
		return getRecords(records);
	}

	@Override
	public List<DnsRecord> getRecords(DnsRecordSearchCriteria searchCriteria) {
		Assert.noNulls(searchCriteria);
		DnsMadeEasySearchCriteria search = new DnsMadeEasySearchCriteria();
		if (searchCriteria.getNameContains().isPresent()) {
			search.setNameContains(searchCriteria.getNameContains().get());
		}
		if (searchCriteria.getValueContains().isPresent()) {
			search.setValueContains(searchCriteria.getValueContains().get());
		}
		if (searchCriteria.getType().isPresent()) {
			search.setType(searchCriteria.getType().get());
		}
		List<DnsMadeEasyDnsRecord> records = getRecords(domain, search);
		return getRecords(records);
	}

	protected List<DnsRecord> getRecords(List<DnsMadeEasyDnsRecord> records) {
		List<DnsRecord> list = new ArrayList<DnsRecord>();
		for (DnsMadeEasyDnsRecord record : records) {
			String name = NullUtils.trimToNone(record.getName());
			String value = NullUtils.trimToNone(record.getData());
			DnsRecord element = new DnsRecord(name, record.getType(), value);
			list.add(element);
		}
		Collections.sort(list);
		return ImmutableList.copyOf(list);
	}

	public DNSMadeEasyServiceContext getContext() {
		return context;
	}

	public String getRestApiUrl() {
		return restApiUrl;
	}

	public DNSMadeEasyCredentials getCredentials() {
		return credentials;
	}

}
