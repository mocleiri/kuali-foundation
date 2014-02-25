package org.kuali.common.dns.dnsme.spring;

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

	public static DNSMadeEasyServiceContext getServiceContext(EnvironmentService env, EncryptionService enc, String restApiUrl, String domain,
			DNSMadeEasyCredentials.Builder encrypted) {
		String url = NullUtils.trimToNull(env.getString(URL_KEY, restApiUrl));
		String domainName = NullUtils.trimToNull(env.getString(DOMAIN_KEY, domain));
		String apiKey = NullUtils.trimToNull(env.getString(API_KEY, encrypted.getApiKey()));
		String secretKey = NullUtils.trimToNull(env.getString(SECRET_KEY, encrypted.getSecretKey()));
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(secretKey);
		}
		DNSMadeEasyCredentials credentials = DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
		return new DNSMadeEasyServiceContext(credentials, url, domainName);
	}
}
