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
package org.kuali.maven.plugins.dnsme.mojo;

import static com.google.common.base.Preconditions.checkState;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Str;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Account;

/**
 *
 */
public abstract class BaseDNSMEMojo extends AbstractMojo {
	/**
	 * The url for the DNSME Rest API
	 * 
	 * @parameter expression="${dnsme.restApiUrl}" default-value="http://api.dnsmadeeasy.com/V1.2"
	 * @required
	 */
	String restApiUrl;

	/**
	 * The REST api key for a DNSME account
	 * 
	 * @parameter expression="${dnsme.apiKey}"
	 * @required
	 */
	String apiKey;

	/**
	 * The REST secret key for a DNSME account
	 * 
	 * @parameter expression="${dnsme.secretKey}"
	 * @required
	 */
	String secretKey;

	/**
	 * @parameter expression="${enc.password}"
	 */
	String encryptionPassword;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Account account = getAccount();
		DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
		performTasks(client);
	}

	protected Account getAccount() {
		Account account = new Account();
		account.setApiKey(demystify(apiKey));
		account.setSecretKey(demystify(secretKey));
		return account;
	}

	protected String demystify(final String string) {
		String demystified = reveal(string);
		return decrypt(demystified);
	}

	protected String decrypt(String string) {
		if (EncUtils.isEncrypted(string)) {
			checkState(!StringUtils.isBlank(encryptionPassword), "[%s] is encrypted but no encryption password was supplied");
			TextEncryptor enc = EncUtils.getTextEncryptor(encryptionPassword);
			return enc.decrypt(EncUtils.unwrap(string));
		} else {
			return string;
		}
	}

	protected String reveal(String string) {
		if (Str.isConcealed(string)) {
			return Str.reveal(string);
		} else {
			return string;
		}
	}

	protected abstract void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException;

	public String getRestApiUrl() {
		return restApiUrl;
	}

	public void setRestApiUrl(String restApiUrl) {
		this.restApiUrl = restApiUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
