package org.kuali.maven.plugins.dnsme;

import java.security.GeneralSecurityException;
import java.util.Date;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.config.PersonalConfig;
import org.kuali.maven.plugins.dnsme.config.ProductionConfig;
import org.kuali.maven.plugins.dnsme.config.SampleConfig;
import org.kuali.maven.plugins.dnsme.config.SandboxConfig;

public class DomainNamesTest {
    DNSMEUtil dnsme = new DNSMEUtil();
    HttpUtil http = new HttpUtil();

    /**
     * Test that values we are generating match up with what is described here:
     * http://cp.dnsmadeeasy.com/enterprisedns/api.html
     */
    @Test
    public void testSampleMatch() throws GeneralSecurityException {
        String date = "Sat, 12 Feb 2011 20:59:04 GMT";
        Account config = new SampleConfig();
        String hmac = dnsme.getHash(config.getSecretKey(), date);
        String expectedHmac = "b3502e6116a324f3cf4a8ed693d78bcee8d8fe3c";
        Assert.assertEquals(expectedHmac, hmac);
    }

    @Test
    public void testPerlMatch() throws GeneralSecurityException {
        String date = "Fri, 21 Oct 2011 20:05:09 GMT";
        Account config = new SandboxConfig();
        String hmac = dnsme.getHash(config.getSecretKey(), date);
        String expectedHmac = "33235fef00493d52ca9200da7ef2fbadd379ef2e";
        Assert.assertEquals(expectedHmac, hmac);
    }

    @Test
    public void testPersonalConfig() throws GeneralSecurityException {
        String url = Constants.SANDBOX_URL + "/domains";
        HttpClient client = http.getHttpClient();
        Account personal = new PersonalConfig();
        HttpRequestResult result = http.executeMethod(client, dnsme.getMethod(personal, url));
        http.log(url, result, -1);
    }

    @Test
    public void testProduction() throws GeneralSecurityException {
        String url = Constants.SANDBOX_URL + "/domains";
        Account sandbox = new ProductionConfig();
        HttpMethod method = dnsme.getMethod(sandbox, url);
        HttpRequestResult result = http.executeMethod(method);
        http.log(url, result, -1);
        System.out.println("Production");
        System.out.println("---------");
        System.out.println(result.getResponseBody());
        Header[] headers = result.getResponseHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + " " + header.getValue());
        }
    }

    @Test
    public void test1() throws GeneralSecurityException {
        Account sandbox = new SandboxConfig();
        String date = dnsme.getHTTPDate(new Date());
        String hmac = dnsme.getHash(sandbox.getSecretKey(), date);
        System.out.println("x-dnsme-apiKey:" + sandbox.getApiKey());
        System.out.println("x-dnsme-requestDate:" + date);
        System.out.println("x-dnsme-hmac:" + hmac);
    }

    @Test
    public void test2() throws GeneralSecurityException {
        String url = Constants.SANDBOX_URL + "/domains";
        Account sandbox = new SandboxConfig();
        HttpMethod method = dnsme.getMethod(sandbox, url);
        HttpRequestResult result = http.executeMethod(method);
        http.log(url, result, -1);
        System.out.println("Sandbox");
        System.out.println("---------");
        System.out.println(result.getResponseBody());
        Header[] headers = result.getResponseHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + " " + header.getValue());
        }
    }

    @Test
    public void test3() {
        HttpUtil inspector = new HttpUtil();
        String url = "http://www.yahoo.com";
        inspector.doWait(url);
    }
}
