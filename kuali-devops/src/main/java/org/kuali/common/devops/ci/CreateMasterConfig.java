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
package org.kuali.common.devops.ci;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.Users;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.ec2.util.ShowLaunchConfigExecutable;
import org.kuali.common.aws.model.AMIs;
import org.kuali.common.aws.model.AvailabilityZones;
import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.devops.aws.SecurityGroups;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.spring.FoundationAwsConfig;
import org.kuali.common.devops.dnsme.ProductionDNSMEContextConfig;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.spring.DNSMEServiceConfig;
import org.kuali.common.dns.util.CreateOrReplaceCNAMEExecutable;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.channel.DefaultSecureChannel;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class, FoundationAwsConfig.class, AwsServiceConfig.class, ProductionDNSMEContextConfig.class,
		DNSMEServiceConfig.class })
public class CreateMasterConfig {

	private static final int TWENTY_FIVE_GIGABYTES = 25;

	@Autowired
	EC2Service service;

	@Autowired
	EC2ServiceContext serviceContext;

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Autowired
	DnsService dns;

	@Autowired
	AwsAccount account;

	@Bean
	public Executable main() {
		LaunchInstanceContext instanceContext = launchInstanceContext();
		Executable show = new ShowLaunchConfigExecutable(serviceContext, instanceContext);
		show.execute();
		Instance instance = service.launchInstance(instanceContext);
		SecureChannel channel = getSecureChannel(instance.getPublicDnsName(), instanceContext.getKeyPair());
		try {
			channel.open();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO eroro", e);
		} finally {
			channel.close();
		}
		return null; // new ExecutablesExecutable(show);
	}

	protected SecureChannel getSecureChannel(String hostname, KeyPair keyPair) {
		Assert.isTrue(keyPair.getPrivateKey().isPresent(), "Private key is required");
		String username = Users.EC2USER.getLogin();
		String rawPrivateKey = keyPair.getPrivateKey().get();
		String privateKey = EncUtils.isEncrypted(rawPrivateKey) ? enc.decrypt(rawPrivateKey) : rawPrivateKey;
		List<String> privateKeyStrings = Collections.singletonList(privateKey);
		return new DefaultSecureChannel.Builder(username, hostname).privateKeyStrings(privateKeyStrings).strictHostKeyChecking(false).useConfigFile(false)
				.includeDefaultPrivateKeyLocations(false).build();
	}

	protected void doDNS(Instance instance) {
		String aliasFQDN = "test.ci.kuali.org";
		Executable cname = new CreateOrReplaceCNAMEExecutable(dns, aliasFQDN, instance.getPublicDnsName());
		cname.execute();
		System.out.println(aliasFQDN + " -> " + instance.getPublicDnsName());
	}

	@Bean
	public LaunchInstanceContext launchInstanceContext() {
		return LaunchUtils.getContext(env, jenkinsMaster());
	}

	@Bean
	public LaunchInstanceContext jenkinsMaster() {
		String ami = AMIs.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09.getId();
		KeyPair keyPair = account.getKeyPair();
		InstanceType type = InstanceType.M1Large;
		String zone = AvailabilityZones.US_EAST_1D.getName();
		List<KualiSecurityGroup> securityGroups = ImmutableList.of(SecurityGroups.CI_MASTER.getGroup(), SecurityGroups.CI.getGroup());
		List<Tag> tags = getTags();
		RootVolume rootVolume = new RootVolume(TWENTY_FIVE_GIGABYTES);
		boolean preventTermination = false; // TODO Switch this back to true before release
		return new LaunchInstanceContext.Builder(ami, keyPair).type(type).availabilityZone(zone).tags(tags).securityGroups(securityGroups).preventTermination(preventTermination)
				.rootVolume(rootVolume).build();
	}

	protected List<Tag> getTags() {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(Tags.Name.MASTER.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Stack.PRODUCTION.getTag());
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		return ImmutableList.copyOf(tags);
	}

}
