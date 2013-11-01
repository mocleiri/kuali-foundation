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

import org.kuali.common.util.Assert;

public class Account {

	public Account(String apiKey, String secretKey, List<Domain> domains) {
		Assert.noBlanks(apiKey, secretKey);
		Assert.noNulls(domains);
		this.apiKey = apiKey;
		this.secretKey = secretKey;
		this.domains = domains;
	}

	private final String apiKey;
	private final String secretKey;
	private final List<Domain> domains;

	public String getApiKey() {
		return apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public List<Domain> getDomains() {
		return domains;
	}

}
