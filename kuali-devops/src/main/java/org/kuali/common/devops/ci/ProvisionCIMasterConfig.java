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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.AMIs;
import org.kuali.common.aws.ec2.model.AvailabilityZones;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.ec2.util.ShowLaunchConfigExecutable;
import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.devops.aws.SecurityGroups;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.spring.FoundationAwsConfig;
import org.kuali.common.devops.aws.sysadmin.ArtifactUtils;
import org.kuali.common.devops.aws.sysadmin.BootstrapExecutable;
import org.kuali.common.devops.aws.sysadmin.InstallZipPackageExecutable;
import org.kuali.common.devops.aws.sysadmin.model.BootstrapContext;
import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.devops.aws.sysadmin.model.Users;
import org.kuali.common.devops.aws.sysadmin.model.ZipPackage;
import org.kuali.common.devops.dnsme.ProductionDNSMEContextConfig;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.spring.DNSMEServiceConfig;
import org.kuali.common.dns.util.CreateOrReplaceCNAMEExecutable;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.spring.DefaultSecureChannelServiceConfig;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class, FoundationAwsConfig.class, AwsServiceConfig.class, ProductionDNSMEContextConfig.class,
		DNSMEServiceConfig.class, DefaultSecureChannelServiceConfig.class })
public class ProvisionCIMasterConfig {

	private static final Logger logger = LoggerFactory.getLogger(ProvisionCIMasterConfig.class);

	private static final int MASTER_ROOT_VOLUME_SIZE_IN_GIGABYTES = 250;
	private static final String MASTER_DNS_NAME = "test.ci.kuali.org";

	@Autowired
	EC2Service ec2;

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

	@Autowired
	SecureChannelService scs;

	@Bean
	public Executable main() {
		long start = System.currentTimeMillis();
		LaunchInstanceContext context = launchInstanceContext();
		new ShowLaunchConfigExecutable(serviceContext, context).execute();
		// Instance instance = ec2.launchInstance(context);
		Instance instance = ec2.getInstance("i-072be77e");
		String privateKey = context.getKeyPair().getPrivateKey().get();
		BootstrapContext bc = new BootstrapContext.Builder(scs, instance.getPublicDnsName(), privateKey).build();
		new BootstrapExecutable(bc).execute();
		List<Executable> executables = new ArrayList<Executable>();
		if (context.getDnsName().isPresent()) {
			String aliasFQDN = context.getDnsName().get();
			String canonicalFQDN = instance.getPublicDnsName();
			Executable cname = new CreateOrReplaceCNAMEExecutable(dns, aliasFQDN, canonicalFQDN);
			// executables.add(cname);
		}
		String username = Users.ROOT.getUser().getLogin();
		String hostname = instance.getPublicDnsName();
		ChannelContext cc = new ChannelContext.Builder(hostname).username(username).privateKey(privateKey).build();
		ZipPackage jdk7zip = new ZipPackage.Builder(ArtifactUtils.getJDK7("1.7.0-u40")).build();
		ZipPackage jdk6zip = new ZipPackage.Builder(ArtifactUtils.getJDK6("1.6.0-u45")).build();
		ZipPackage tomcatZip = new ZipPackage.Builder("tomcat", ArtifactUtils.getTomcat("7.0.26")).build();
		InstallZipPackageContext jdk7 = new InstallZipPackageContext.Builder(scs, cc, jdk7zip).build();
		InstallZipPackageContext jdk6 = new InstallZipPackageContext.Builder(scs, cc, jdk6zip).build();
		InstallZipPackageContext tomcat = new InstallZipPackageContext.Builder(scs, cc, tomcatZip).build();
		executables.add(new InstallZipPackageExecutable(jdk6));
		executables.add(new InstallZipPackageExecutable(jdk7));
		executables.add(new InstallZipPackageExecutable(tomcat));
		// new ConcurrentExecutables(executables,true).execute();
		new ExecutablesExecutable(executables, true).execute();
		long elapsed = System.currentTimeMillis() - start;
		logger.info("Elapsed: {}", FormatUtils.getTime(elapsed));
		return null; // new ExecutablesExecutable(show);
	}

	@Bean
	public LaunchInstanceContext launchInstanceContext() {
		return LaunchUtils.getContext(env, jenkinsMaster());
	}

	@Bean
	public LaunchInstanceContext jenkinsMaster() {
		String ami = AMIs.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09.getId();
		KeyPair keyPair = EncUtils.decrypt(enc, account.getKeyPair());
		InstanceType type = InstanceType.M1Large;
		String zone = AvailabilityZones.US_EAST_1D.getName();
		List<KualiSecurityGroup> securityGroups = ImmutableList.of(SecurityGroups.CI_MASTER.getGroup(), SecurityGroups.CI.getGroup());
		List<Tag> tags = getTags();
		RootVolume rootVolume = new RootVolume(MASTER_ROOT_VOLUME_SIZE_IN_GIGABYTES);
		boolean preventTermination = false; // TODO Switch this back to true before release
		boolean overrideExistingSecurityGroupPermissions = true;
		String dnsName = MASTER_DNS_NAME;
		return new LaunchInstanceContext.Builder(ami, keyPair).type(type).availabilityZone(zone).tags(tags).securityGroups(securityGroups).preventTermination(preventTermination)
				.rootVolume(rootVolume).overrideExistingSecurityGroupPermissions(overrideExistingSecurityGroupPermissions).dnsName(dnsName).build();
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