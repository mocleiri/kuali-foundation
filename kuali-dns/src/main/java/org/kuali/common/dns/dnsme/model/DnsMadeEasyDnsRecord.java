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
package org.kuali.common.dns.dnsme.model;

import org.kuali.common.dns.model.DnsRecordType;

public class DnsMadeEasyDnsRecord {

	DnsMadeEasyDomain domain;
	Integer id;
	String name;
	DnsRecordType type;
	String data;
	Integer ttl;
	DnsMadeEasyGTDLocation gtdLocation;
	String password;
	String description;
	String keywords;
	String title;
	String redirectType;
	Boolean hardLink;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DnsRecordType getType() {
		return type;
	}

	public void setType(DnsRecordType type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getTtl() {
		return ttl;
	}

	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public Boolean getHardLink() {
		return hardLink;
	}

	public void setHardLink(Boolean hardLink) {
		this.hardLink = hardLink;
	}

	public DnsMadeEasyDomain getDomain() {
		return domain;
	}

	public void setDomain(DnsMadeEasyDomain domain) {
		this.domain = domain;
	}

	public DnsMadeEasyGTDLocation getGtdLocation() {
		return gtdLocation;
	}

	public void setGtdLocation(DnsMadeEasyGTDLocation gtdLocation) {
		this.gtdLocation = gtdLocation;
	}
}
