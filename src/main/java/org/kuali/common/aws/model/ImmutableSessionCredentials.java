/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSSessionCredentials;

public class ImmutableSessionCredentials implements AWSSessionCredentials {

	public ImmutableSessionCredentials(String accessKey, String secretKey, String sessionToken) {
		Assert.noBlanks(accessKey, secretKey, sessionToken);
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.sessionToken = sessionToken;
	}

	private final String accessKey;
	private final String secretKey;
	private final String sessionToken;

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

	@Override
	public String getSessionToken() {
		return sessionToken;
	}

}
