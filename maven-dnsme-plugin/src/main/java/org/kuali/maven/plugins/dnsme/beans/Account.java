package org.kuali.maven.plugins.dnsme.beans;

import java.util.List;

public class Account {
    List<Domain> domains;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
}
