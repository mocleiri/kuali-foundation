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
import org.kuali.common.dns.dnsme.model.Domain;
import org.kuali.common.dns.dnsme.model.DomainNames;
import org.kuali.common.dns.dnsme.model.Record;
import org.kuali.common.dns.dnsme.model.RecordComparator;
import org.kuali.common.dns.dnsme.model.Search;
import org.kuali.common.dns.http.HttpRequestResult;
import org.kuali.common.dns.http.HttpUtil;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.dns.model.DnsRecordType;
import org.kuali.common.dns.util.DnsUtils;
import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DNSMadeEasyService implements DnsService {

	public static final int HTTP_OK = 200;
	public static final int HTTP_CREATED = 201;

	private final DNSMadeEasyServiceContext context;
	private final Domain domain;
	private final String restApiUrl;
	private final DNSMadeEasyCredentials credentials;

	//
	private final Gson gson = new Gson();
	private final HttpUtil http = new HttpUtil();
	private final DNSMEUtil dnsme = new DNSMEUtil();

	public DNSMadeEasyService(DNSMadeEasyServiceContext context) {
		Assert.noNulls(context);
		this.context = context;
		this.domain = getDomain(context.getDomain());
		this.restApiUrl = context.getRestApiUrl();
		this.credentials = context.getCredentials();
	}

	public List<Domain> getDomains() {
		String url = this.restApiUrl + "/domains";
		String json = getJson(url, HTTP_OK);
		DomainNames domainNames = gson.fromJson(json, DomainNames.class);
		return getDomains(domainNames);
	}

	public Domain getDomain(String name) {
		List<Domain> domains = getDomains();
		for (Domain domain : domains) {
			if (domain.getName().equalsIgnoreCase(name)) {
				return domain;
			}
		}
		return null;
	}

	public Domain addDomain(Domain domain) {
		String url = this.restApiUrl + "/domains/" + domain.getName();
		PutMethod method = new PutMethod(url);
		return addOrUpdateObject(url, HTTP_CREATED, domain, method);
	}

	public void deleteDomain(Domain domain) {
		String url = this.restApiUrl + "/domains/" + domain.getName();
		deleteObject(url);
	}

	protected String getQueryString(Search search) {
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

	protected List<NameValuePair> getPairs(Search search) {
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

	public Record getRecord(Domain domain, Search search) {
		List<Record> records = getRecords(domain, search);
		if (records.size() != 1) {
			throw new IllegalStateException("Search criteria must match exactly 1 record but it matched " + records.size() + " records");
		} else {
			return records.get(0);
		}
	}

	public List<Record> getRecords(Domain domain, Search search) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
		if (search != null) {
			url += getQueryString(search);
		}
		String json = getJson(url, HTTP_OK);
		List<Record> records = getRecords(json);
		for (Record record : records) {
			record.setDomain(domain);
		}
		return records;
	}

	public Search getSearch(String name) {
		Search search = new Search();
		search.setName(name);
		return search;
	}

	public Record getRecord(Domain domain, String name) {
		return getRecord(domain, getSearch(name));
	}

	public Record getRecord(Domain domain, int recordId) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + recordId;
		String resultJson = getJson(url, HTTP_OK);
		Record resultRecord = gson.fromJson(resultJson, Record.class);
		return resultRecord;
	}

	protected void validateForUpdate(Record record) {
		if (record.getId() == null && record.getName() == null) {
			throw new IllegalStateException("Either id or name must have a value when updating");
		}
	}

	public void updateRecord(Domain domain, Record record) {
		validateForUpdate(record);
		if (record.getId() == null) {
			Record existingRecord = getRecord(domain, record.getName());
			record.setId(existingRecord.getId());
		}
		validateRecord(record);
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + record.getId();
		PutMethod method = new PutMethod(url);
		addOrUpdateObject(url, HTTP_OK, record, method);
	}

	public Record addRecord(Domain domain, Record record) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
		if (record.getId() != null) {
			throw new IllegalStateException("id must be null when adding");
		}
		validateRecord(record);
		PostMethod method = new PostMethod(url);
		return addOrUpdateObject(url, HTTP_CREATED, record, method);
	}

	public void deleteRecord(Domain domain, int recordId) {
		String url = this.restApiUrl + "/domains/" + domain.getName() + "/records/" + recordId;
		deleteObject(url);
	}

	public void deleteRecord(Domain domain, String name) {
		Record record = getRecord(domain, name);
		Assert.isTrue(record.getId() != null, "id is required");
		deleteRecord(domain, record.getId());
	}

	protected void deleteObject(String url) {
		HttpMethod method = dnsme.getDeleteMethod(credentials, url);
		HttpRequestResult result = http.executeMethod(method);
		validateResult(result, HTTP_OK);
	}

	public List<Record> getCNAMERecords(Domain domain) {
		Search search = new Search();
		search.setType(DnsRecordType.CNAME);
		List<Record> records = getRecords(domain, search);
		Collections.sort(records, new RecordComparator());
		return records;
	}

	protected void validateRecord(Record record) {
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

	public List<Record> getRecords(Domain domain) {
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

	protected List<Record> getRecords(String json) {
		Type recordsListType = new TypeToken<List<Record>>() {
		}.getType();

		@SuppressWarnings("unchecked")
		List<Record> records = (List<Record>) gson.fromJson(json, recordsListType);
		if (records == null) {
			return new ArrayList<Record>();
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

	protected List<Domain> getDomains(DomainNames domainNames) {
		if (domainNames.getList() == null) {
			return new ArrayList<Domain>();
		}
		List<String> names = domainNames.getList();
		List<Domain> domains = new ArrayList<Domain>();
		for (String name : names) {
			Domain domain = new Domain(credentials, name);
			domains.add(domain);
		}
		return domains;
	}

	@Override
	public String getDomain() {
		return context.getDomain();
	}

	protected void validateDomain(String fqdn) {
		Assert.isTrue(fqdn.endsWith(getDomain()), "[" + fqdn + "] doesn't end with [" + getDomain() + "]");
	}

	@Override
	public DnsRecord createCNAMERecord(String aliasFQDN, String fqdn, int timeToLiveInSeconds) {

		// Make sure both are syntactically valid fully qualified domain names
		DnsUtils.validateFQDN(aliasFQDN);
		DnsUtils.validateFQDN(fqdn);

		// The alias must be in our domain
		validateDomain(aliasFQDN);

		// TTL can't be negative
		Assert.noNegatives(timeToLiveInSeconds);

		// The dot at the end is a magic value telling DNSME that this is a fully qualified domain name
		// Without it, DNSME auto-appends our domain name
		fqdn = fqdn + ".";

		// Create a Record object
		Record record = new Record();
		record.setType(DnsRecordType.CNAME);
		record.setTtl(timeToLiveInSeconds);
		record.setData(fqdn);
		record.setName(aliasFQDN);
		record.setDomain(domain);

		// Actually add the record
		Record added = addRecord(domain, record);

		// Convert to a DnsRecord and return
		return new DnsRecord(added.getName(), added.getType(), record.getData());
	}

	@Override
	public boolean exists(String fqdn) {
		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only check for the existence of fqdn's in our domain
		validateDomain(fqdn);

		// Setup a search object based on the fqdn
		Search search = getSearch(fqdn);

		// Get a list of matching records from DNSME
		List<Record> records = getRecords(domain, search);

		// If there is more than 1 record, something has gone wrong
		Assert.isTrue(records.size() <= 1, "Found " + records.size() + " records when expecting a max of 1");

		// Size can only be zero or one here
		// Return true if size is one
		// We found a record matching the fqdn they gave us
		return records.size() == 1;
	}

	@Override
	public void delete(String fqdn) {
		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only delete fqdn's in our domain
		validateDomain(fqdn);

		// If it exists, delete it
		if (exists(fqdn)) {
			deleteRecord(domain, fqdn);
		}
	}

	@Override
	public DnsRecord getRecord(String fqdn) {
		// Make sure it's a valid fully qualified domain name
		DnsUtils.validateFQDN(fqdn);

		// Can only get DNS records for fqdn's in our domain
		validateDomain(fqdn);

		// Setup a search object based on the fqdn
		Search search = getSearch(fqdn);

		// Get a list of matching records from DNSME
		List<Record> records = getRecords(domain, search);

		// If there is more than 1 record, something has gone wrong
		Assert.isTrue(records.size() == 1, "Found " + records.size() + " records when expecting exactly 1");

		// Extract the first (and only) item in the list
		Record record = records.get(0);

		// Create a new DnsRecord from the DNSME record
		return new DnsRecord(record.getName(), record.getType(), record.getData());
	}

	@Override
	public List<DnsRecord> getRecords() {
		List<Record> records = getRecords(domain);
		return getRecords(records);
	}

	@Override
	public List<DnsRecord> getRecords(DnsRecordSearchCriteria searchCriteria) {
		Assert.noNulls(searchCriteria);
		Search search = new Search();
		if (searchCriteria.getNameContains().isPresent()) {
			search.setNameContains(searchCriteria.getNameContains().get());
		}
		if (searchCriteria.getValueContains().isPresent()) {
			search.setValueContains(searchCriteria.getValueContains().get());
		}
		if (searchCriteria.getType().isPresent()) {
			search.setType(searchCriteria.getType().get());
		}
		List<Record> records = getRecords(domain, search);
		return getRecords(records);
	}

	protected List<DnsRecord> getRecords(List<Record> records) {
		List<DnsRecord> list = new ArrayList<DnsRecord>();
		for (Record record : records) {
			DnsRecord element = new DnsRecord(record.getName(), record.getType(), record.getData());
			list.add(element);
		}
		return ImmutableList.copyOf(list);
	}

}
