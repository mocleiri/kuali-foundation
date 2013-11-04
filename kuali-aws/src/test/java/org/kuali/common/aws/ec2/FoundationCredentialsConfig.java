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
package org.kuali.common.aws.ec2;

import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.aws.model.util.CredentialUtils;
import org.kuali.common.aws.spring.AwsCredentialsConfig;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.auth.AWSCredentials;

@Configuration
@Import({ DefaultEncryptionServiceConfig.class })
public class FoundationCredentialsConfig implements AwsCredentialsConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Override
	@Bean
	public AWSCredentials awsCredentials() {
		AWSCredentials credentials = CredentialUtils.getCredentials(env, enc, Credentials.FOUNDATION);
		return new ImmutableAwsCredentials(credentials);
	}

}
