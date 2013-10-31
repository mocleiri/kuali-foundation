/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.aws.spring;

import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

@Configuration
@Import({ DefaultEncryptionServiceConfig.class })
public class DefaultAwsCredentialsConfig implements AwsCredentialsConfig {

	private static final String ACCESS_KEY = "aws.accessKeyId";
	private static final String ACCESS_ENV_KEY = "AWS_ACCESS_KEY_ID";

	private static final String SECRET_KEY = "aws.secretKey";
	private static final String SECRET_ENV_KEY = "AWS_SECRET_KEY";

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService service;

	@Override
	@Bean
	public AWSCredentials awsCredentials() {
		String accessKey = getValue(ACCESS_KEY, ACCESS_ENV_KEY);
		String secretKey = getValue(SECRET_KEY, SECRET_ENV_KEY);
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = service.decrypt(secretKey);
		}
		return new ImmutableAwsCredentials(accessKey, secretKey);
	}

	protected String getValue(String key, String envKey) {
		Optional<String> value = SpringUtils.getOptionalString(env, key);
		if (value.isPresent()) {
			return value.get();
		} else {
			return env.getString(envKey);
		}
	}

}
