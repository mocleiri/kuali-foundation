package org.kuali.maven.plugins.dnsme;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Test;

public class JSONTest {

    @Test
    public void test1() {
        try {
            String jsonText = "{\"name\":\"foomanchu.com\"}";
            JSONObject jsonObject = JSONObject.fromObject(jsonText);
            Domain domain = (Domain) JSONObject.toBean(jsonObject, Domain.class);
            System.out.println(domain.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String jsonText = "{\"list\":[\"foomanchu.com\"]}";
            JSONObject jsonObject = JSONObject.fromObject(jsonText);
            List<Domain> domains = (List<Domain>) JSONObject.toBean(jsonObject, ArrayList.class);
            System.out.println(domains);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
