package org.kuali.common.dns.dnsme.spring;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

public class DNSMadeEasyUtils {

	private static final String API_KEY = "dnsme.apiKey";
	private static final String SECRET_KEY = "dnsme.secretKey";
	private static final String URL_KEY = "dnsme.url";
	private static final String DOMAIN_KEY = "dnsme.domain";

	private static final DNSMadeEasyServiceContext NONE = new DNSMadeEasyServiceContext(new DNSMadeEasyCredentials(NullUtils.NONE, NullUtils.NONE), URLS.PRODUCTION, NullUtils.NONE);

	public static DnsService getService(EnvironmentService env, EncryptionService enc) {
		DNSMadeEasyServiceContext context = getServiceContext(env, enc, NONE);
		return new DNSMadeEasyDnsService(context);
	}

	public static DNSMadeEasyServiceContext getServiceContext(EnvironmentService env, EncryptionService enc, DNSMadeEasyServiceContext provided) {
		String url = NullUtils.trimToNull(env.getString(URL_KEY, provided.getRestApiUrl()));
		String domainName = NullUtils.trimToNull(env.getString(DOMAIN_KEY, provided.getDomainName()));
		DNSMadeEasyCredentials encrypted = provided.getCredentials();
		String apiKey = NullUtils.trimToNull(env.getString(API_KEY, encrypted.getApiKey()));
		String secretKey = NullUtils.trimToNull(env.getString(SECRET_KEY, encrypted.getSecretKey()));
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(secretKey);
		}
		DNSMadeEasyCredentials credentials = new DNSMadeEasyCredentials(apiKey, secretKey);
		return new DNSMadeEasyServiceContext(credentials, url, domainName);
	}
}
