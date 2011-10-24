package org.kuali.maven.plugins.dnsme.beans;

import java.util.List;

public class Domain {
    String name;
    List<String> nameServers;
    Boolean gtdEnabled;

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

}
