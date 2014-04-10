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

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.amazonaws.auth.AWSCredentials;

public final class ImmutableAWSCredentials implements AWSCredentials {

	public static ImmutableAWSCredentials copyOf(AWSCredentials credentials) {
		if (credentials instanceof ImmutableAWSCredentials) {
			return (ImmutableAWSCredentials) credentials;
		} else {
			return new ImmutableAWSCredentials(credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey());
		}
	}

	public ImmutableAWSCredentials(String accessKey, String secretKey) {
		this.accessKey = checkNotBlank(accessKey, "accessKey");
		this.secretKey = checkNotBlank(secretKey, "secretKey");
	}

	private final String accessKey;
	private final String secretKey;

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}
