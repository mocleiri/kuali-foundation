package org.kuali.maven.plugins;

import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.plugins.dnsme.config.Config;
import org.kuali.maven.plugins.dnsme.config.ProductionConfig;
import org.kuali.maven.plugins.dnsme.config.SampleConfig;
import org.kuali.maven.plugins.dnsme.config.SandboxConfig;

public class ApiTest {

    @Test
    public void testHeaderValues() {
        String date = "Sat, 12 Feb 2011 20:59:04 GMT";
        Config config = new SampleConfig();
        Api api = new Api(config);
        String hmac = api.getHMACSHA1Hash(date);
        String expectedHmac = "b3502e6116a324f3cf4a8ed693d78bcee8d8fe3c";
        Assert.assertEquals(hmac, expectedHmac);
    }

    // @Test
    public void test1() {
        Config config = new SandboxConfig();
        Api api = new Api(config);
        String date = api.getHTTPDate(new Date());
        String hmac = api.getHMACSHA1Hash(date);
        System.out.println("x-dnsme-apiKey:" + config.getApiKey());
        System.out.println("x-dnsme-requestDate:" + date);
        System.out.println("x-dnsme-hmac:" + hmac);
    }

    // @Test
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
