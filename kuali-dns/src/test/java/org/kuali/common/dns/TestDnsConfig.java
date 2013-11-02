/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.dns;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.Account;
import org.kuali.common.dns.dnsme.model.Accounts;
import org.kuali.common.dns.dnsme.model.Domain;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class })
public class TestDnsConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Bean
	public Executable main() {
		Account encryptedAccount = Accounts.SANDBOX.getAccount();
		String apiKey = encryptedAccount.getApiKey();
		String encryptedSecretKey = encryptedAccount.getSecretKey();
		String secretKey = enc.decrypt(encryptedSecretKey);
		Account account = new Account(apiKey, secretKey);
		DnsService service = new DNSMadeEasyService(account, URLS.PRODUCTION);
		try {
			Domain domain = service.getDomain("kuali.org");
			System.out.println(domain.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
