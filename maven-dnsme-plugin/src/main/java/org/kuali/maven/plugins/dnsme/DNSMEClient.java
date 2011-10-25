package org.kuali.maven.plugins.dnsme;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.DomainNames;
import org.kuali.maven.plugins.dnsme.beans.Record;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DNSMEClient {

    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;

    String restApiUrl;
    Account account;
    Gson gson = new Gson();
    HttpUtil http = new HttpUtil();
    DNSMEUtil dnsme = new DNSMEUtil();

    public static DNSMEClient getInstance(Account account, String restApiUrl) {
        return new DNSMEClient(account, restApiUrl);
    }

    private DNSMEClient(Account account, String restApiUrl) {
        super();
        this.account = account;
        this.restApiUrl = restApiUrl;
    }

    protected List<Domain> getDomains(DomainNames domainNames) {
        if (domainNames.getList() == null) {
            return new ArrayList<Domain>();
        }
        List<String> names = domainNames.getList();
        List<Domain> domains = new ArrayList<Domain>();
        for (String name : names) {
            Domain domain = new Domain();
            domain.setAccount(account);
            domain.setName(name);
            domains.add(domain);
        }
        return domains;
    }

    public List<Domain> getDomains() {
        String url = this.restApiUrl + "/domains";
        String json = getJson(url, HTTP_OK);
        DomainNames domainNames = gson.fromJson(json, DomainNames.class);
        return getDomains(domainNames);
    }

    public List<Record> getRecords(Domain domain, RecordType type) {
        String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
        if (type != null) {
            url += "?type=" + type;
        }
        String json = getJson(url, HTTP_OK);
        List<Record> records = getRecords(json);
        for (Record record : records) {
            record.setDomain(domain);
        }
        return records;
    }

    public Record addRecord(Domain domain, Record record) {
        String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
        String json = gson.toJson(record);
        HttpMethod method = dnsme.getPostMethod(account, url, json);
        String resultJson = getJson(url, method, HTTP_CREATED);
        Record resultRecord = gson.fromJson(resultJson, Record.class);
        return resultRecord;
    }

    public List<Record> getCNAMERecords(Domain domain) {
        List<Record> records = getRecords(domain, RecordType.CNAME);
        Collections.sort(records, new RecordComparator());
        return records;
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
        HttpMethod method = dnsme.getMethod(account, url);
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
            throw new DNSMEException(result.getException());
        case TIMEOUT:
            throw new DNSMEException("Operation timed out");
        case COMPLETED:
            int code = result.getStatusCode();
            if (statusCode == result.getStatusCode()) {
                return;
            } else {
                String errorText = result.getResponseBody();
                throw new DNSMEException("Invalid http status '" + code + ":" + result.getStatusText()
                        + "' Expected: '" + statusCode + "'  Error:" + errorText);
            }
        default:
            throw new DNSMEException("Unknown result type: " + result.getType());
        }
    }

}
