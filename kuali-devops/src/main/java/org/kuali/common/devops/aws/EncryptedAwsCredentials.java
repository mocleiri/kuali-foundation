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
package org.kuali.common.devops.aws;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.amazonaws.auth.AWSCredentials;

public enum EncryptedAwsCredentials implements AWSCredentials {

	FOUNDATION("U2FsdGVkX1+8Ztcpl1oE1zvItuWZ+U/B1m1Y5ZfnPL1/RL3ytfTW0GPQ0T2I6f3x", "U2FsdGVkX19iFvmWrQbvBA+aoUjCW37k8hFzcRrDzjc6KDOfEYUd7m8/nyvy/YBZghfgT88GJhlQVk4aD02eWg=="), //
	STUDENT("U2FsdGVkX19hBXKp9N3JwJMV3n7AMldCEv3BMRKVngUNmsfIk7QfRRxgJ7bSnX1O", "U2FsdGVkX18dh07mozYEQfy5uzzLZ2gEf8hWlkSViADnBBfsgeHUd3as46GSFGr/z3UKbaYDC6UR/kaK44a14g=="), //
	RICE("U2FsdGVkX19rqcUXCR56ecKvRbotNWUDIjItUkNCSm3nbeW09G/26j/KtajdzdZr", "U2FsdGVkX184pbCFaZNEuXKKqaNcRgwCrdyWLvcaXKcQtrh6WdGGk+7TTlFnw9+a75NcHL+cLMl/XZiq5t1erw=="), //
	OLE("U2FsdGVkX1++jaZ6LfP3GjJlj+UUUZIQ+2/xOVSrivUUQ6AxM3XPcEC1lECkh7zv", "U2FsdGVkX196mJBHpZOJKOYxd9fPupAzv9Vf6b9BRHk+IgXKUMQKjwrAUOlFl/WJlPLeotD5QndCbhwJmgNflw==");

	private final String accessKey;
	private final String secretKey;

	private EncryptedAwsCredentials(String accessKey, String secretKey) {
		this.accessKey = checkNotBlank(accessKey, "accessKey");
		this.secretKey = checkNotBlank(secretKey, "secretKey");
	}

	@Override
	public String getAWSAccessKeyId() {
		return accessKey;
	}

	@Override
	public String getAWSSecretKey() {
		return secretKey;
	}

}
