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
import org.kuali.common.util.channel.ChannelContext;
import org.kuali.common.util.channel.ChannelUtils;
import org.kuali.common.util.channel.ConnectionContext;
import org.kuali.common.util.channel.DefaultSecureChannel;
import org.kuali.common.util.channel.RemoteFile;
import org.kuali.common.util.channel.Result;
import org.kuali.common.util.channel.SecureChannel;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.execute.Executable;
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
		DNSMEServiceConfig.class })
public class CreateMasterConfig {

	private static final Logger logger = LoggerFactory.getLogger(CreateMasterConfig.class);

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
		// Instance instance = service.launchInstance(instanceContext);
		Instance instance = service.getInstance("i-61100618");
		// enableRootSSH(instance, instanceContext);
		doRoot(instance, instanceContext);
		// doDNS(instance);
		return null; // new ExecutablesExecutable(show);
	}

	protected void doRoot(Instance instance, LaunchInstanceContext context) {
		KeyPair keyPair = context.getKeyPair();
		String privateKey = keyPair.getPrivateKey().get();
		String privateKeyMaterial = EncUtils.isEncrypted(privateKey) ? enc.decrypt(privateKey) : privateKey;
		ChannelContext cc = new ChannelContext.Builder(privateKeyMaterial).build();
		String username = Users.ROOT.getLogin();
		String hostname = instance.getPublicDnsName();
		ConnectionContext conn = new ConnectionContext.Builder(username, hostname).build();
		SecureChannel channel = new DefaultSecureChannel(cc);
		try {
			channel.open(conn);
			String cmd = "pwd; ls -la";
			Result result = ChannelUtils.exec(channel, cmd);
			logger.info("\n{}\n{}\n", cmd, result.getStdout());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			channel.close();
		}
	}

	protected void enableRootSSH(Instance instance, LaunchInstanceContext context) {
		KeyPair keyPair = context.getKeyPair();
		String privateKey = keyPair.getPrivateKey().get();
		String privateKeyMaterial = EncUtils.isEncrypted(privateKey) ? enc.decrypt(privateKey) : privateKey;
		ChannelContext cc = new ChannelContext.Builder(privateKeyMaterial).build();
		String username = Users.EC2USER.getLogin();
		String hostname = instance.getPublicDnsName();
		ConnectionContext conn = new ConnectionContext.Builder(username, hostname).requestPseudoTerminal(true).build();
		SecureChannel channel = new DefaultSecureChannel(cc);
		try {
			String src = "classpath:org/kuali/common/kuali-devops/amazon-linux/2013.09/etc/ssh/sshd_config";
			String path = "/home/ec2-user/sshd_config";
			String command1 = "sudo cp /home/ec2-user/.ssh/authorized_keys /root/.ssh/authorized_keys";
			String command2 = "sudo cp " + path + " /etc/ssh/sshd_config";
			String command3 = "sudo service sshd restart";
			RemoteFile dst = new RemoteFile.Builder(path).build();
			channel.open(conn);
			exec(channel, command1); // copy authorized_keys from ec2-user to root as that version does not have the header commands blocking ssh
			logger.info("cp {} {}", src, dst.getAbsolutePath());
			channel.copyLocationToFile(src, dst); // copy the updated sshd_config file into the ec2-users home directory
			exec(channel, command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
			exec(channel, command3); // restart the sshd service
			logger.info("rm {}", path);
			channel.deleteFile(path); // delete the sshd_config file we left in the ec2-users home directory
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			channel.close();
		}
	}

	protected void exec(SecureChannel channel, String command) {
		ChannelUtils.exec(channel, command, true);
	}

	protected void doDNS(Instance instance) {
		String aliasFQDN = "test.ci.kuali.org";
		String canonicalFQDN = instance.getPublicDnsName();
		Executable cname = new CreateOrReplaceCNAMEExecutable(dns, aliasFQDN, canonicalFQDN);
		cname.execute();
		logger.info(aliasFQDN + " -> " + instance.getPublicDnsName());
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
		boolean overrideExistingSecurityGroupPermissions = true;
		return new LaunchInstanceContext.Builder(ami, keyPair).type(type).availabilityZone(zone).tags(tags).securityGroups(securityGroups).preventTermination(preventTermination)
				.rootVolume(rootVolume).overrideExistingSecurityGroupPermissions(overrideExistingSecurityGroupPermissions).build();
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