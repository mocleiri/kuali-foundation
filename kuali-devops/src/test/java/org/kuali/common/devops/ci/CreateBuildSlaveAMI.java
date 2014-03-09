package org.kuali.common.devops.ci;

import static com.amazonaws.services.ec2.model.InstanceType.C3Xlarge;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI_BUILD_SLAVE;
import static org.kuali.common.devops.project.KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER;
import static org.kuali.common.dns.model.CNAMEContext.newCNAMEContext;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.KeyPairBuilders;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.devops.project.KualiDevOpsProjectConstants;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.model.CNAMEContext;
import org.kuali.common.dns.util.CreateOrReplaceCNAME;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.service.DefaultExecContext;
import org.kuali.common.util.service.DefaultExecService;
import org.kuali.common.util.service.ExecContext;
import org.kuali.common.util.service.ExecService;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class CreateBuildSlaveAMI {

	private static final Logger logger = newLogger();
	private final VirtualSystem vs = VirtualSystem.create();
	private final String ami = "ami-83dee0ea";
	private final InstanceType type = C3Xlarge;
	private final RootVolume rootVolume = RootVolume.create(64, true);
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), CI_BUILD_SLAVE.getGroup());
	private final List<Tag> tags = getTags();
	private final String subdomain = "slave.ci";
	private final String domain = "kuali.org";
	private final String distro = "ubuntu" + vs.getFileSeparator() + "12.04";
	private final String bashScript = "jenkins.sh";
	private final String svnPassword = "enc--PAqzT//IpbTfzhsnLyumedsE7yon7yqi";
	private final String nexusPassword = "enc--/ROzksAX9W5r3CrLMefr9d+C5cIqkDtw";

	@Test
	public void test() {
		try {
			Instance instance = getNewSlaveInstance();
			// Instance instance = getRunningSlaveInstance("i-385fa21b");
			logger.info(format("public dns: %s", instance.getPublicDnsName()));
			updateDns(instance);
			CanonicalFile buildDir = getBuildDirectory();
			chmod(buildDir);
			CanonicalFile bashDir = getLocalBashDir(buildDir);
			configureSlave(bashDir);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void configureSlave(File bashDir) {
		ExecContext context = getExecContext(bashDir);
		ExecService service = new DefaultExecService();
		service.execute(context);
	}

	protected ExecContext getExecContext(File bashDir) {
		List<String> args = ImmutableList.of(Auth.decrypt(nexusPassword), Auth.decrypt(svnPassword), subdomain, "slave", "-qq");
		String executable = bashDir.getAbsolutePath() + vs.getFileSeparator() + bashScript;
		DefaultExecContext context = new DefaultExecContext();
		context.setWorkingDirectory(bashDir);
		context.setExecutable(executable);
		context.setArgs(args);
		return context;
	}

	protected CanonicalFile getLocalBashDir(File buildDir) {
		String path = ProjectUtils.getResourcePath(KUALI_DEVOPS_PROJECT_IDENTIFIER) + vs.getFileSeparator() + distro + vs.getFileSeparator() + "local";
		CanonicalFile localBashDir = new CanonicalFile(buildDir, path);
		checkState(localBashDir.exists(), "%s does not exist", localBashDir);
		checkState(localBashDir.isDirectory(), "%s is not a directory", localBashDir);
		return localBashDir;
	}

	protected void chmod(CanonicalFile dir) {
		ExecService service = new DefaultExecService();
		List<String> args = ImmutableList.of("-R", "755", dir.getPath());
		service.execute("chmod", args);
	}

	protected CanonicalFile getBuildDirectory() {
		Optional<CodeSource> src = fromNullable(KualiDevOpsProjectConstants.class.getProtectionDomain().getCodeSource());
		checkState(src.isPresent(), "could not get code source");
		URL url = src.get().getLocation();
		try {
			URI uri = url.toURI();
			CanonicalFile file = new CanonicalFile(uri);
			checkState(file.exists(), "[%s] does not exist");
			checkState(file.isDirectory(), "[%s] is not a directory");
			return file;
		} catch (URISyntaxException e) {
			throw illegalState(e);
		}
	}

	protected void updateDns(Instance instance) {
		DnsService service = getDnsService();
		CNAMEContext context = getCNAMEContext(instance.getPublicDnsName());
		new CreateOrReplaceCNAME(service, context).execute();
	}

	protected CNAMEContext getCNAMEContext(String canonicalFQDN) {
		String aliasFQDN = subdomain + "." + domain;
		return newCNAMEContext(aliasFQDN, canonicalFQDN);
	}

	protected DnsService getDnsService() {
		DNSMadeEasyCredentials credentials = Auth.getDnsmeCredentials();
		DNSMadeEasyServiceContext context = new DNSMadeEasyServiceContext(credentials, URLS.PRODUCTION, domain);
		return new DNSMadeEasyDnsService(context);
	}

	protected List<Tag> getTags() {
		List<Tag> tags = newArrayList();
		// TODO Change this to PRODUCTION when ready
		tags.add(Tags.Stack.TESTING.getTag());
		tags.add(Tags.Name.SLAVE.getTag());
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		return ImmutableList.copyOf(tags);
	}

	protected Instance getRunningSlaveInstance(String instanceId) {
		EC2Service service = getEC2Service();
		return service.getInstance(instanceId);
	}

	protected Instance getNewSlaveInstance() {
		EC2Service service = getEC2Service();
		KeyPair keyPair = Auth.getKeyPair(KeyPairBuilders.FOUNDATION.getBuilder());
		LaunchInstanceContext context = LaunchInstanceContext.builder(ami, keyPair).withType(type).withRootVolume(rootVolume).withSecurityGroups(securityGroups).withTags(tags)
				.build();
		return service.launchInstance(context);
	}

	protected EC2Service getEC2Service() {
		AWSCredentials creds = Auth.getAwsCredentials("foundation");
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext ec = EC2ServiceContext.create(creds);
		return new DefaultEC2Service(ec, ws);
	}

}
