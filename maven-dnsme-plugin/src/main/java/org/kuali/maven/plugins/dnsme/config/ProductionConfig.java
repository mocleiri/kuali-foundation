package org.kuali.maven.plugins.dnsme.config;

import org.kuali.maven.plugins.dnsme.beans.Account;

public class ProductionConfig extends Account {
    public ProductionConfig() {
        super();
        setApiKey("454f2836-81c0-4379-b8dd-2cc6495131b5");
        setSecretKey("5fc3245f-78b5-4c92-9053-be0b5c64a680");
    }
}
