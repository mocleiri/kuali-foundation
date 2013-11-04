package org.kuali.common.dns.spring;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.Credentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.spring.env.EnvironmentService;

public class DNSMadeEasyConfigUtils {

	private static final String API_KEY = "dnsme.apiKey";
	private static final String SECRET_KEY = "dnsme.secretKey";
	private static final String URL_KEY = "dnsme.url";
	private static final String DOMAIN_KEY = "dnsme.domain";

	public static DnsService getService(EnvironmentService env, EncryptionService enc, String domainName) {
		DNSMadeEasyServiceContext context = getServiceContext(env, enc, domainName);
		return new DNSMadeEasyService(context);
	}

	public static DNSMadeEasyServiceContext getServiceContext(EnvironmentService env, EncryptionService enc, String defaultDomainName) {
		DNSMadeEasyCredentials encrypted = Credentials.PRODUCTION.getCredentials();
		String url = env.getString(URL_KEY, URLS.PRODUCTION);
		String apiKey = env.getString(API_KEY, encrypted.getApiKey());
		String secretKey = env.getString(SECRET_KEY, encrypted.getSecretKey());
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(secretKey);
		}
		DNSMadeEasyCredentials credentials = new DNSMadeEasyCredentials(apiKey, secretKey);
		String domainName = env.getString(DOMAIN_KEY, defaultDomainName);
		return new DNSMadeEasyServiceContext(credentials, url, domainName);
	}
}
