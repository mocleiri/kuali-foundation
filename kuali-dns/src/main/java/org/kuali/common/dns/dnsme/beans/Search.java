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


public class Search {

    GTDLocation gtdLocation; // {DEFAULT, US_EAST, US_WEST, ASIA}
    RecordType type; // {A, CNAME, MX, NS, PTR, SRV, AAAA, HTTPRED, TXT}
    String name; // - this is an exact match
    String nameContains; // - looks for any record containing your input in the name
    String value; // - this is an exact match on the value of the record
    String valueContains; // - looks for any record containing your input in the value

    public GTDLocation getGtdLocation() {
        return gtdLocation;
    }

    public void setGtdLocation(GTDLocation gtdLocation) {
        this.gtdLocation = gtdLocation;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameContains() {
        return nameContains;
    }

    public void setNameContains(String nameContains) {
        this.nameContains = nameContains;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueContains() {
        return valueContains;
    }

    public void setValueContains(String valueContains) {
        this.valueContains = valueContains;
    }

}
