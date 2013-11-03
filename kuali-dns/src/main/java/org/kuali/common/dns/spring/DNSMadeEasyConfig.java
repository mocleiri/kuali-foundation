package org.kuali.common.dns.spring;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.Account;
import org.kuali.common.dns.dnsme.model.Accounts;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class })
public class DNSMadeEasyConfig implements DnsConfig {

	private static final String API_KEY = "dnsme.apiKey";
	private static final String SECRET_KEY = "dnsme.secretKey";
	private static final String URL_KEY = "dnsme.url";

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Override
	public DnsService dnsService() {
		Account encrypted = Accounts.PRODUCTION.getAccount();
		String url = env.getString(URL_KEY, URLS.PRODUCTION);
		String apiKey = env.getString(API_KEY, encrypted.getApiKey());
		String secretKey = env.getString(SECRET_KEY, encrypted.getSecretKey());
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(secretKey);
		}
		Account account = new Account(apiKey, secretKey);
		return new DNSMadeEasyService(account, url);
	}

}
