package org.kuali.common.dns.dnsme.spring;

import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;
import static org.kuali.common.util.nullify.NullUtils.trimToNull;

import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.util.encrypt.Encryptor;
import org.kuali.common.util.spring.env.EnvironmentService;

public class DNSMadeEasyUtils {

	private static final String API_KEY = "dnsme.apiKey";
	private static final String SECRET_KEY = "dnsme.secretKey";
	private static final String URL_KEY = "dnsme.url";
	private static final String DOMAIN_KEY = "dnsme.domain";

	public static DNSMadeEasyServiceContext getServiceContext(EnvironmentService env, String restApiUrl, String domain, DNSMadeEasyCredentials encrypted) {
		Encryptor encryptor = getDefaultEncryptor();
		String url = trimToNull(env.getString(URL_KEY, restApiUrl));
		String domainName = trimToNull(env.getString(DOMAIN_KEY, domain));
		String apiKey = encryptor.decrypt(trimToNull(env.getString(API_KEY, encrypted.getApiKey())));
		String secretKey = encryptor.decrypt(trimToNull(env.getString(SECRET_KEY, encrypted.getSecretKey())));
		DNSMadeEasyCredentials credentials = DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
		return new DNSMadeEasyServiceContext(credentials, url, domainName);
	}
}
