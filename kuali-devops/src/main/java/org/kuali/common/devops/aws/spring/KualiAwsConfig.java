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
package org.kuali.common.devops.aws.spring;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.util.CredentialUtils;
import org.kuali.common.aws.spring.AwsAccountConfig;
import org.kuali.common.aws.spring.AwsCredentialsConfig;
import org.kuali.common.devops.aws.AwsUtils;
import org.kuali.common.dns.spring.DomainNameConfig;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.auth.AWSCredentials;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class })
public class KualiAwsConfig implements AwsAccountConfig, AwsCredentialsConfig, DomainNameConfig {

	private static final String DOMAIN_NAME = "kuali.org";

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Override
	@Bean
	public AwsAccount awsAccount() {
		return AwsUtils.getAwsAccount(env);
	}

	@Override
	@Bean
	public String domainName() {
		return DOMAIN_NAME;
	}

	@Override
	@Bean
	public AWSCredentials awsCredentials() {
		return CredentialUtils.getCredentials(env, enc, awsAccount().getCredentials());
	}

}
