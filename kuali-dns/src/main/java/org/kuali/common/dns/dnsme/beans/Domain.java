/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.dns.dnsme.beans;

import java.util.List;

public class Domain {

    Account account;
    String name;
    List<String> nameServer;
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
        this.nameServer = nameServers;
        this.gtdEnabled = gtdEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNameServer() {
        return nameServer;
    }

    public void setNameServer(List<String> nameServers) {
        this.nameServer = nameServers;
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
