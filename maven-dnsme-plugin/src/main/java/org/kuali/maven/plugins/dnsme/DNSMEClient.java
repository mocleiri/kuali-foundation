package org.kuali.maven.plugins.dnsme;

import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.DomainNames;
import org.kuali.maven.plugins.dnsme.beans.Record;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DNSMEClient {

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
                throw new DNSMEException("Invalid http status '" + code + ":" + result.getStatusText()
                        + "' Expected: '" + statusCode + "'");
            }
        default:
            throw new DNSMEException("Unknown result type: " + result.getType());
        }
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
        try {
            String url = this.restApiUrl + "/domains";
            String json = getJson(url, 200);
            DomainNames domainNames = gson.fromJson(json, DomainNames.class);
            return getDomains(domainNames);
        } catch (GeneralSecurityException e) {
            throw new DNSMEException(e);
        }
    }

    public List<Record> getRecords(Domain domain) {
        try {
            String url = this.restApiUrl + "/domains/" + domain.getName() + "/records";
            String json = getJson(url, 200);
            List<Record> records = getRecords(json);
            for (Record record : records) {
                record.setDomain(domain);
            }
            return records;
        } catch (GeneralSecurityException e) {
            throw new DNSMEException(e);
        }
    }

    protected String getJson(String url, int successCode) throws GeneralSecurityException {
        HttpMethod method = dnsme.getMethod(account, url);
        HttpRequestResult result = http.executeMethod(method);
        validateResult(result, successCode);
        return result.getResponseBody();
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

}
