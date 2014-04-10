package org.kuali.common.devops.dnsme;

import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;

public enum EncryptedDNSMECredentials {

	PRODUCTION("QvPcbYfgeT8o8eJcVGO/MnaBckeUSr+fOm2Q/emZV+SQzmAHBGb/l+0AU5tdX2Hs", "13Ba852eRs+PjjLD6EYICtsF30A2MprT/8rwrIV12RbmQ8j2m4TAqYXJvtgjGOsI");

	private final DNSMadeEasyCredentials credentials;

	private EncryptedDNSMECredentials(String apiKey, String secretKey) {
		this.credentials = DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
	}

	public DNSMadeEasyCredentials getCredentials() {
		return credentials;
	}

}
