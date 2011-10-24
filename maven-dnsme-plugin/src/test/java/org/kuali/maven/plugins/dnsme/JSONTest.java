package org.kuali.maven.plugins.dnsme;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;
import org.kuali.maven.plugins.dnsme.beans.Account;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.DomainNames;

import com.google.gson.Gson;

public class JSONTest {

    // @Test
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
        Account fromJson = gson.fromJson(jsonText, Account.class);
        List<Domain> domainList = fromJson.getDomains();
        Domain kualiDotNet = domainList.get(0);
        Assert.assertNotNull(fromJson.getDomains());
        Assert.assertEquals("kuali.net", kualiDotNet.getName());
    }

    @Test
    public void testJsonToJavaWithGson() {
        String jsonText = "{\"list\":[\"kuali.net\",\"kuali.org\",\"kualiproject.org\"]}";
        JSONObject jsonObject = JSONObject.fromObject(jsonText);
        DomainNames domainNames = (DomainNames) JSONObject.toBean(jsonObject, DomainNames.class);
        Assert.assertEquals(3, domainNames.getList().size());
    }

    @Test
    public void test1() {
        String jsonText = "{\"list\":[\"kuali.net\",\"kuali.org\",\"kualiproject.org\"]}";
        JSONObject jsonObject = JSONObject.fromObject(jsonText);
        DomainNames domainNames = (DomainNames) JSONObject.toBean(jsonObject, DomainNames.class);
        Assert.assertEquals(3, domainNames.getList().size());
    }

}