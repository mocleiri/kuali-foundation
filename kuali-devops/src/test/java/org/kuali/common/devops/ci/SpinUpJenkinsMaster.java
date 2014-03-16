package org.kuali.common.devops.ci;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI_MASTER;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getBasicLaunchRequest;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getEC2Service;
import static org.kuali.common.devops.logic.Auth.getDnsmeCredentials;
import static org.kuali.common.devops.project.KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER;
import static org.kuali.common.dns.model.CNAMEContext.newCNAMEContext;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.log.LoggerLevel.INFO;
import static org.kuali.common.util.log.LoggerLevel.WARN;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.kuali.common.util.maven.RepositoryUtils.getDefaultLocalRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.AMI;
import org.kuali.common.aws.ec2.model.Distro;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.model.CNAMEContext;
import org.kuali.common.dns.util.CreateOrReplaceCNAME;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.impl.DefaultChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.CommandContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.condition.Condition;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.project.DefaultProjectService;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.stream.LoggingStreamConsumer;
import org.kuali.common.util.stream.NoOpStreamConsumer;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitContext;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class SpinUpJenkinsMaster {

	private static final Logger logger = newLogger();

	private final Stopwatch sw = createStarted();
	private final VirtualSystem vs = VirtualSystem.create();
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), CI_MASTER.getGroup());
	private final String gpgPassphraseEncrypted = "coSLMPP2IsSAXYVp9NIsvxzqAkd7N+Yh";
	private final String amazonAccount = "foundation";
	private static final String DOMAIN = "kuali.org";
	private final Distro distro = Distro.UBUNTU;
	private final String distroVersion = "12.04";
	private static final String ROOT = "root";
	private static final String UBUNTU = "ubuntu";

	// TODO Change these when ready
	private static final Tag NAME = Tags.Name.MASTER_BETA.getTag();
	private static final Tag STACK = Tags.Stack.TEST.getTag();
	private static final int DEFAULT_ROOT_VOLUME_SIZE = 32;
	private static final AMI DEFAULT_AMI = AMI.UBUNTU_64_BIT_PRECISE_LTS_1204_US_WEST;

	// These should be fine assuming the lines above get changed
	private final List<Tag> tags = getMasterTags(NAME, STACK);

	@Test
	public void test() {
		try {
			String dnsPrefix = getProperty("dns.prefix", "ci");
			String aliasFqdn = Joiner.on('.').join(dnsPrefix, DOMAIN);
			// Default to quiet mode unless they've supplied -Dec2.quiet=false
			boolean quiet = equalsIgnoreCase(getProperty("ec2.quiet"), "false") ? false : true;
			logger.info(vs.getOs().getName());
			KeyPair keyPair = CreateBuildSlaveAMI.KUALI_KEY;
			String privateKey = keyPair.getPrivateKey().get();
			BasicLaunchRequest request = getMasterLaunchRequest();
			ProjectIdentifier pid = KUALI_DEVOPS_PROJECT_IDENTIFIER;

			EC2Service service = getEC2Service(amazonAccount);
			Instance instance = CreateBuildSlaveAMI.launchAndWait(service, request, securityGroups, tags);
			// Instance instance = service.getInstance("i-b593c4ea");
			info("public dns: %s", instance.getPublicDnsName());
			updateDns(instance, aliasFqdn);
			String dns = instance.getPublicDnsName();
			// While spinning things up, use the Amazon DNS name since the DNSME alias can take a while (few minutes) to propagate
			verifySSH(UBUNTU, dns, privateKey);
			info("[%s] is online with ssh - %s", dns, FormatUtils.getTime(sw));
			bootstrap(dns, privateKey);
			SecureChannel channel = openSecureChannel(ROOT, dns, privateKey, quiet);
			String basedir = publishProject(channel, pid, ROOT, dns, quiet);
			String gpgPassphrase = Auth.decrypt(gpgPassphraseEncrypted);
			String common = getBashScript(basedir, pid, distro, distroVersion, "jenkins/configurecommon");
			String master = getBashScript(basedir, pid, distro, distroVersion, "jenkins/configuremaster");
			String quietFlag = (quiet) ? "-q" : "";
			setupEssentials(channel, basedir, pid, distro, distroVersion, gpgPassphrase, dnsPrefix, quietFlag);
			exec(channel, common, quietFlag, aliasFqdn, gpgPassphrase);
			exec(channel, master, quietFlag, aliasFqdn, "1.532.2", gpgPassphrase);

			// The spin up process should have given DNS enough time to settle down
			info("Verifying SSH to -> [%s]", aliasFqdn);
			verifySSH(ROOT, aliasFqdn, privateKey);
			info("[%s] jenkins is ready - %s", aliasFqdn, FormatUtils.getTime(sw));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static void setupEssentials(SecureChannel channel, String basedir, ProjectIdentifier pid, Distro distro, String distroVersion, String gpgPassphrase,
			String dnsPrefix, String quietFlag) {
		String basics = getBashScript(basedir, pid, distro, distroVersion, "common/configurebasics");
		String sethostname = getBashScript(basedir, pid, distro, distroVersion, "common/sethostname");
		String java = getBashScript(basedir, pid, distro, distroVersion, "common/installjava");
		String tomcat = getBashScript(basedir, pid, distro, distroVersion, "common/installtomcat");
		exec(channel, basics, quietFlag);
		exec(channel, sethostname, dnsPrefix, DOMAIN);
		exec(channel, java, quietFlag, "jdk6", "u45", gpgPassphrase);
		exec(channel, java, quietFlag, "jdk7", "u51", gpgPassphrase);
		exec(channel, tomcat, quietFlag, "tomcat7", "jdk7", gpgPassphrase);
	}

	protected static void bootstrap(String hostname, String privateKey) throws IOException {
		info("[%s] enabling root ssh", hostname);
		enableRootSSH(UBUNTU, hostname, privateKey);
	}

	protected static String publishProject(SecureChannel channel, ProjectIdentifier pid, String username, String hostname, boolean quiet) {
		ProjectService service = new DefaultProjectService(new BasicEnvironmentService());
		Project project = service.getProject(pid);
		info(project.getArtifactId());
		Artifact artifact = new Artifact.Builder(project.getGroupId(), project.getArtifactId(), project.getVersion()).build();
		File repo = getDefaultLocalRepository();
		CanonicalFile jar = new CanonicalFile(RepositoryUtils.getFile(repo, artifact));
		String filename = project.getArtifactId() + ".jar";
		String directory = format("/mnt/%s", project.getArtifactId());
		RemoteFile remote = new RemoteFile.Builder("/mnt/" + filename).build();
		String to = username + "@" + hostname + ":" + remote.getAbsolutePath();
		info("scp:from -> %s", jar);
		info("scp:to   -> %s", to);
		channel.scp(jar, remote);
		info("unpack   -> %s to %s", remote.getAbsolutePath(), directory);
		execFormattedCommand(channel, quiet, "apt-get install unzip -y");
		execFormattedCommand(channel, quiet, "rm -rf %s", directory);
		execFormattedCommand(channel, quiet, "unzip -o %s -d %s", remote.getAbsolutePath(), directory);
		execFormattedCommand(channel, quiet, "chmod -R 755 %s", directory);
		return directory;
	}

	protected static ChannelContext.Builder getSilentContextBuilder(String hostname) {
		ChannelContext.Builder builder = new ChannelContext.Builder(hostname);
		builder.echo(false);
		builder.debug(false);
		return builder;
	}

	protected static String getBashScript(String basedir, ProjectIdentifier project, Distro distro, String version, String script) {
		List<String> tokens = Lists.newArrayList();
		tokens.add(basedir);
		tokens.addAll(Splitter.on('.').splitToList(project.getGroupId()));
		tokens.add(project.getArtifactId());
		tokens.add(distro.getName());
		tokens.add(version);
		tokens.add(script);
		return Joiner.on('/').join(tokens);
	}

	protected static void exec(SecureChannel channel, String command, String arg) {
		exec(channel, command, singletonList(arg));
	}

	protected static void exec(SecureChannel channel, String command, String... args) {
		exec(channel, command, ImmutableList.copyOf(args));
	}

	protected static void exec(SecureChannel channel, String command, List<String> args) {
		StreamConsumer stdout = new LoggingStreamConsumer(logger, INFO);
		StreamConsumer stderr = new LoggingStreamConsumer(logger, WARN);
		String cmd = command + " " + Joiner.on(' ').join(args);
		CommandContext context = new CommandContext.Builder(cmd).stdout(stdout).stderr(stderr).build();
		channel.exec(context);
	}

	protected static void execFormattedCommand(SecureChannel channel, boolean quiet, String command, Object... args) {
		StreamConsumer stdout = (quiet) ? NoOpStreamConsumer.INSTANCE : new LoggingStreamConsumer(logger, INFO);
		StreamConsumer stderr = (quiet) ? NoOpStreamConsumer.INSTANCE : new LoggingStreamConsumer(logger, WARN);
		String formatted = formatString(command, args);
		CommandContext context = new CommandContext.Builder(formatted).stdout(stdout).stderr(stderr).build();
		channel.exec(context);
	}

	protected static SecureChannel openSecureChannel(String username, String hostname, String privateKey, boolean quiet) throws IOException {
		ChannelContext context = getSilentContextBuilder(hostname).echo(!quiet).debug(!quiet).username(username).privateKey(privateKey).connectTimeout(getMillisAsInt("5s"))
				.build();
		ChannelService service = new DefaultChannelService();
		return service.openChannel(context);
	}

	protected static void enableRootSSH(String username, String hostname, String privateKey) throws IOException {
		SecureChannel channel = openSecureChannel(username, hostname, privateKey, true);
		execFormattedCommand(channel, true, "sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys");
	}

	protected void verifySSH(String username, String hostname, String privateKey) {
		WaitContext context = WaitContext.builder(getMillisAsInt("10m")).sleepMillis(getMillisAsInt("5s")).build();
		WaitService service = new DefaultWaitService();
		Condition condition = getSshCondition(username, hostname, privateKey);
		service.wait(context, condition);
	}

	protected Condition getSshCondition(String username, String hostname, String privateKey) {
		ChannelContext context = new ChannelContext.Builder(hostname).username(username).privateKey(privateKey).connectTimeout(getMillisAsInt("5s")).build();
		ChannelService service = new DefaultChannelService();
		return new VerifiedSSHCondition(service, context);
	}

	protected void updateDns(Instance instance, String alias) {
		String canonical = instance.getPublicDnsName();
		CNAMEContext context = newCNAMEContext(alias, canonical);
		DnsService service = getDnsService();
		new CreateOrReplaceCNAME(service, context).execute();
	}

	protected DnsService getDnsService() {
		DNSMadeEasyServiceContext context = new DNSMadeEasyServiceContext(getDnsmeCredentials(), URLS.PRODUCTION, DOMAIN);
		return new DNSMadeEasyDnsService(context);
	}

	protected static BasicLaunchRequest getMasterLaunchRequest() {
		BasicLaunchRequest.Builder builder = BasicLaunchRequest.builder();
		builder.setTimeoutMillis(getMillisAsInt("15m"));
		builder.setAmi(DEFAULT_AMI.getId());
		builder.setRootVolume(RootVolume.create(DEFAULT_ROOT_VOLUME_SIZE, true));
		return getBasicLaunchRequest(builder.build());
	}

	protected static List<Tag> getMasterTags(Tag name, Tag stack) {
		List<Tag> tags = newArrayList();
		tags.addAll(CreateBuildSlaveAMI.getCommonTags(stack));
		tags.add(name);
		return ImmutableList.copyOf(tags);
	}

	protected static void info(String msg, Object... args) {
		logger.info(formatString(msg, args));
	}

	protected static String formatString(String s, Object... args) {
		if (args != null && args.length > 0) {
			return format(s, args);
		} else {
			return s;
		}
	}

}
