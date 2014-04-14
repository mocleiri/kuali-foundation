package org.kuali.common.devops.dnsme;

import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;

public enum EncryptedDNSMECredentials {

	PRODUCTION("U2FsdGVkX18d3prd5c5/M/xU6tCkPz6idK8m8VWZxEGNMarPLSVsIoZezEFUmZ0OK81Gs8IdytOfAlfqrXz2yA==", "U2FsdGVkX1/WnqUvodEf1xBGnD0ShVLu6q5nKAKzpMohsVzSch8PTKx+7VSOPeSOBTcwIn6PVM9Z8Dgy3gXayQ==");

	private final DNSMadeEasyCredentials credentials;

	private EncryptedDNSMECredentials(String apiKey, String secretKey) {
		this.credentials = DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
	}

	public DNSMadeEasyCredentials getCredentials() {
		return credentials;
	}

}
