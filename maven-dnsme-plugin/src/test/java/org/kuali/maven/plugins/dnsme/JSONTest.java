package org.kuali.maven.plugins.dnsme;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.beans.Domain;

public class JSONTest {

    @Test
    public void testJavaToJson() {
        try {
            List<Domain> domains = new ArrayList<Domain>();

            // String jsonText = "{\"name\":\"foomanchu.com\"}";
            String jsonText = "{\"list\":[\"kuali.net\",\"kuali.org\",\"kualiproject.org\"]}";
            JSONObject jsonObject = JSONObject.fromObject(jsonText);
            Account account = (Account) JSONObject.toBean(jsonObject, Account.class);
            System.out.println(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try {
            // String jsonText = "{\"name\":\"foomanchu.com\"}";
            String jsonText = "{\"list\":[\"kuali.net\",\"kuali.org\",\"kualiproject.org\"]}";
            JSONObject jsonObject = JSONObject.fromObject(jsonText);
            Account account = (Account) JSONObject.toBean(jsonObject, Account.class);
            System.out.println(account);
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