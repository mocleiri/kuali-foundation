package org.kuali.common.util.encrypt;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class EncryptionResult {

	public EncryptionResult(String initializationVector, String encryptedText) {
		this.initializationVector = checkNotBlank(initializationVector, "initializationVector");
		this.encryptedText = checkNotBlank(encryptedText, "encryptedText");
	}

	private final String initializationVector;
	private final String encryptedText;

	public String getInitializationVector() {
		return initializationVector;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

}
