package org.kuali.maven.plugins.dnsme;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.DomainNames;

import com.google.gson.Gson;

public class DNSMEClient {

    String restApiUrl;
    Account account;
    Gson gson = new Gson();
    HttpUtil http = new HttpUtil();
    DNSMEUtil dnsme = new DNSMEUtil();

    public static DNSMEClient getInstance(Account account, String restApiUrl) {
        return new DNSMEClient(account, restApiUrl);
    }

    private DNSMEClient(Account account, String baseUrl) {
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
            HttpMethod method = dnsme.getMethod(account, url);
            HttpRequestResult result = http.executeMethod(method);
            validateResult(result, 200);
            String json = result.getResponseBody();
            DomainNames domainNames = gson.fromJson(json, DomainNames.class);
            return getDomains(domainNames);
        } catch (GeneralSecurityException e) {
            throw new DNSMEException(e);
        }

    }
}
