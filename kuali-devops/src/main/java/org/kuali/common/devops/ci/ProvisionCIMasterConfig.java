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
import org.kuali.common.aws.ec2.model.AMI;
import org.kuali.common.aws.ec2.model.AvailabilityZones;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.util.LaunchUtils;
import org.kuali.common.aws.ec2.util.ShowLaunchConfig;
import org.kuali.common.aws.model.AwsContext;
import org.kuali.common.aws.spring.AwsServiceConfig;
import org.kuali.common.devops.aws.SecurityGroups;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.spring.FoundationAwsConfig;
import org.kuali.common.devops.aws.sysadmin.ArtifactUtils;
import org.kuali.common.devops.aws.sysadmin.Bootstrap;
import org.kuali.common.devops.aws.sysadmin.InstallJDK;
import org.kuali.common.devops.aws.sysadmin.InstallTomcat;
import org.kuali.common.devops.aws.sysadmin.ShowAwsContext;
import org.kuali.common.devops.aws.sysadmin.model.BootstrapContext;
import org.kuali.common.devops.aws.sysadmin.model.Heap;
import org.kuali.common.devops.aws.sysadmin.model.Heaps;
import org.kuali.common.devops.aws.sysadmin.model.InstallTomcatContext;
import org.kuali.common.devops.aws.sysadmin.model.InstallZipContext;
import org.kuali.common.devops.aws.sysadmin.model.Users;
import org.kuali.common.devops.aws.sysadmin.model.Zip;
import org.kuali.common.devops.dnsme.ProductionDNSMEContextConfig;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.spring.DNSMEServiceConfig;
import org.kuali.common.dns.util.CreateOrReplaceCNAME;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.spring.DefaultSecureChannelServiceConfig;
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
	AwsContext awsContext;

	@Autowired
	ChannelService scs;

	@Bean
	public Executable main() {
		long start = System.currentTimeMillis();
		LaunchInstanceContext context = launchInstanceContext();
		new ShowAwsContext(awsContext).execute();
		new ShowLaunchConfig(serviceContext, context).execute();
		Instance instance = ec2.launchInstance(context);
		// Instance instance = ec2.getInstance("i-838d95e4");
		String privateKey = awsContext.getAuth().getKeyPair().getPrivateKey().get();
		BootstrapContext bc = new BootstrapContext.Builder(scs, instance.getPublicDnsName(), privateKey).build();
		new Bootstrap(bc).execute();
		List<Executable> executables = new ArrayList<Executable>();
		if (context.getDnsName().isPresent()) {
			String aliasFQDN = context.getDnsName().get();
			String canonicalFQDN = instance.getPublicDnsName();
			Executable cname = new CreateOrReplaceCNAME(dns, aliasFQDN, canonicalFQDN);
			executables.add(cname);
		}
		String username = Users.ROOT.getUser().getLogin();
		String hostname = instance.getPublicDnsName();
		ChannelContext channel = new ChannelContext.Builder(hostname).username(username).privateKey(privateKey).build();
		Zip jdk7 = new Zip.Builder(ArtifactUtils.getJDK7("1.7.0-u40")).build();
		Zip jdk6 = new Zip.Builder(ArtifactUtils.getJDK6("1.6.0-u45")).build();
		Heap heap = Heaps.asMap().get(instance.getInstanceType());
		InstallJDK jdk = getJDKInstaller(channel, jdk7);
		executables.add(getJDKInstaller(channel, jdk6));
		executables.add(jdk);
		String javaHome = jdk.getContext().getInstallDir();
		executables.add(getTomcatInstaller(channel, javaHome));
		// new ConcurrentExecutables.Builder(executables).timed(true).build().execute();
		new ExecutablesExecutable(executables, false, true).execute();
		long elapsed = System.currentTimeMillis() - start;
		logger.info("Elapsed: {}", FormatUtils.getTime(elapsed));
		return null; // new ExecutablesExecutable(show);
	}

	protected Executable getTomcatInstaller(ChannelContext channel, String javaHome) {
		InstallTomcatContext context = new InstallTomcatContext.Builder(scs, channel).build();
		return new InstallTomcat.Builder(context).build();
	}

	protected InstallJDK getJDKInstaller(ChannelContext channel, Zip zip) {
		InstallZipContext context = new InstallZipContext.Builder(scs, channel, zip).build();
		return new InstallJDK.Builder(context).build();
	}

	@Bean
	public LaunchInstanceContext launchInstanceContext() {
		return LaunchUtils.getContext(env, jenkinsMaster());
	}

	@Bean
	public LaunchInstanceContext jenkinsMaster() {
		String ami = AMI.AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09.getId();
		KeyPair keyPair = awsContext.getAuth().getKeyPair();
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