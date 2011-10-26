package org.kuali.maven.plugins.dnsme;

import org.junit.Test;

public class RecordsTest {
    DNSMEUtil dnsme = new DNSMEUtil();
    HttpUtil http = new HttpUtil();

    @Test
    public void testOne() {
        /*
        String url = Constants.PRODUCTION_URL + "/domains/kuali.org/records";
        Account sandbox = new ProductionAccount();
        HttpMethod method = dnsme.getGetMethod(sandbox, url);
        HttpRequestResult result = http.executeMethod(method);
        http.log(url, result, -1);
        System.out.println("Production");
        System.out.println("---------");
        System.out.println(result.getResponseBody());
        Header[] headers = result.getResponseHeaders();
        for (Header header : headers) {
            System.out.println(header.getName() + " " + header.getValue());
        }*/
    }
}
