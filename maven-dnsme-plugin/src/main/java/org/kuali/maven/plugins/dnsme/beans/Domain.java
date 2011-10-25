package org.kuali.maven.plugins.dnsme.beans;

import java.util.List;

public class Domain {

    Account account;
    String name;
    List<String> nameServers;
    Boolean gtdEnabled;
    List<Record> records;

    public Domain() {
        this(null);
    }

    public Domain(String name) {
        this(name, null, null);
    }

    public Domain(String name, List<String> nameServers, Boolean gtdEnabled) {
        super();
        this.name = name;
        this.nameServers = nameServers;
        this.gtdEnabled = gtdEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNameServers() {
        return nameServers;
    }

    public void setNameServers(List<String> nameServers) {
        this.nameServers = nameServers;
    }

    public Boolean getGtdEnabled() {
        return gtdEnabled;
    }

    public void setGtdEnabled(Boolean gtdEnabled) {
        this.gtdEnabled = gtdEnabled;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
