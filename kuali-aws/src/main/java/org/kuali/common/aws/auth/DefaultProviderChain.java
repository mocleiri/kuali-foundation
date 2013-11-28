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
package org.kuali.common.aws.auth;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Assert;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.google.common.base.Optional;

/**
 * This chain searches for AWS credentials in system properties -> environment variables -> Amazon's EC2 Instance Metadata Service
 */
public final class DefaultProviderChain extends AWSCredentialsProviderChain {

	public DefaultProviderChain() {
		super(getProviders(Optional.<AWSCredentials> absent()));
	}

	public DefaultProviderChain(AWSCredentials credentials) {
		super(getProviders(Optional.of(credentials)));
	}

	private DefaultProviderChain(Optional<AWSCredentials> credentials) {
		super(getProviders(credentials));
	}

	private static AWSCredentialsProvider[] getProviders(Optional<AWSCredentials> credentials) {

		// Null not allowed
		Assert.noNulls(credentials);

		// Set up some storage
		List<AWSCredentialsProvider> providers = new ArrayList<AWSCredentialsProvider>();

		// System properties always win
		providers.add(new SystemPropertiesCredentialsProvider());

		// Then fall through to environment variables
		providers.add(new EnvironmentVariableCredentialsProvider());

		// Then fall through to the provided credentials if they are present
		if (credentials.isPresent()) {
			providers.add(new SimpleAWSCredentialsProvider(credentials.get()));
		}

		// Finally fall through to Amazon's EC2 Instance Metadata Service
		// http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/java-dg-roles.html
		// This allows you to setup an IAM role, attach that role to an EC2 Instance at launch time,
		// and thus automatically authorize java code running on an EC2 instance
		providers.add(new InstanceProfileCredentialsProvider());

		// Convert the list into an array
		return providers.toArray(new AWSCredentialsProvider[providers.size()]);
	}

}
