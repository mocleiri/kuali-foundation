package org.kuali.maven.plugins;

import java.security.GeneralSecurityException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.dnsme.config.Config;
import org.kuali.maven.plugins.dnsme.config.PersonalConfig;
import org.kuali.maven.plugins.dnsme.config.ProductionConfig;
import org.kuali.maven.plugins.dnsme.config.SampleConfig;
import org.kuali.maven.plugins.dnsme.config.SandboxConfig;

public class ApiTest {
    RestUtil restUtil = new RestUtil();

    /**
     * Test that values we are generating match up with what is described here:
     * http://cp.dnsmadeeasy.com/enterprisedns/api.html
     */
    @Test
    public void testSampleMatch() throws GeneralSecurityException {
        String date = "Sat, 12 Feb 2011 20:59:04 GMT";
        Config config = new SampleConfig();
        String hmac = restUtil.getHash(config.getSecretKey(), date);
        String expectedHmac = "b3502e6116a324f3cf4a8ed693d78bcee8d8fe3c";
        Assert.assertEquals(expectedHmac, hmac);
    }

    @Test
    public void testPerlMatch() throws GeneralSecurityException {
        String date = "Fri, 21 Oct 2011 20:05:09 GMT";
        Config config = new SandboxConfig();
        String hmac = restUtil.getHash(config.getSecretKey(), date);
        String expectedHmac = "33235fef00493d52ca9200da7ef2fbadd379ef2e";
        Assert.assertEquals(expectedHmac, hmac);
    }

    @Test
    public void testPersonalConfig() {
        try {
            HttpInspector inspector = new HttpInspector();
            HttpClient client = inspector.getDefaultHttpClient();
            Config personal = new PersonalConfig();
            HttpRequestResult result = inspector.doDNSMERequest(client, personal);
            inspector.log(personal.getBaseUrl(), result, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void test1() throws GeneralSecurityException {
        Config config = new SandboxConfig();
        String date = restUtil.getHTTPDate(new Date());
        String hmac = restUtil.getHash(config.getSecretKey(), date);
        System.out.println("x-dnsme-apiKey:" + config.getApiKey());
        System.out.println("x-dnsme-requestDate:" + date);
        System.out.println("x-dnsme-hmac:" + hmac);
    }

    @Test
    public void test2() {
        try {
            HttpInspector inspector = new HttpInspector();
            HttpClient client = inspector.getDefaultHttpClient();
            Config production = new ProductionConfig();
            Config sandbox = new SandboxConfig();
            HttpRequestResult result1 = inspector.doDNSMERequest(client, production);
            HttpRequestResult result2 = inspector.doDNSMERequest(client, sandbox);
            inspector.log(production.getBaseUrl(), result1, -1);
            inspector.log(sandbox.getBaseUrl(), result2, -1);
            System.out.println("Producton");
            System.out.println("---------");
            System.out.println(result1.getResponseBody());
            System.out.println("Sandbox");
            System.out.println("---------");
            System.out.println(result2.getResponseBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            HttpInspector inspector = new HttpInspector();
            String url = "http://www.yahoo.com";
            inspector.doWait(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
