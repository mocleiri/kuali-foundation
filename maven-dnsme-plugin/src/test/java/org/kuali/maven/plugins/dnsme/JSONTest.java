package org.kuali.maven.plugins.dnsme;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;

import com.google.gson.Gson;

public class JSONTest {

    @Test
    public void testJavaToJson() {
        List<Domain> domains = new ArrayList<Domain>();
        domains.add(new Domain("kuali.net"));
        domains.add(new Domain("kuali.org"));
        domains.add(new Domain("kualiproject.org"));
        Account account = new Account();
        account.setDomains(domains);
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(account);
        String jsonText = jsonObject.toString();
        JSONObject fromText = JSONObject.fromObject(jsonText);
        Account fromJsonObject = (Account) JSONObject.toBean(fromText, Account.class);
        Assert.assertNotNull(fromJsonObject.getDomains());
        List<Domain> domainList = fromJsonObject.getDomains();
        for (Domain domain : domainList) {
            System.out.println(domain.getName());
        }
    }

    @Test
    public void testJavaToJsonWithGson() {
        List<Domain> domains = new ArrayList<Domain>();
        domains.add(new Domain("kuali.net"));
        domains.add(new Domain("kuali.org"));
        domains.add(new Domain("kualiproject.org"));
        Account account = new Account();
        account.setDomains(domains);
        Gson gson = new Gson();
        String jsonText = gson.toJson(account);
        System.out.println(jsonText);
        Account fromJson = gson.fromJson(jsonText, Account.class);
        List<Domain> domainList = fromJson.getDomains();
        for (Domain domain : domainList) {
            System.out.println(domain.getName());
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