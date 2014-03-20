package org.kuali.common.devops.ci;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newTreeMap;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping.INSTANCE_STORE_0;
import static org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping.INSTANCE_STORE_1;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI_BUILD_SLAVE;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.exec;
import static org.kuali.common.devops.ci.SpinUpJenkinsMaster.getResource;
import static org.kuali.common.devops.ci.model.Constants.DOMAIN;
import static org.kuali.common.devops.ci.model.Constants.GPG_PASSPHRASE_ENCRYPTED;
import static org.kuali.common.devops.ci.model.Constants.ROOT;
import static org.kuali.common.devops.ci.model.Constants.UBUNTU;
import static org.kuali.common.devops.project.KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.CreateAMIRequest;
import org.kuali.common.aws.ec2.model.Distro;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping;
import org.kuali.common.aws.ec2.model.ImmutableTag;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.ci.model.BasicLaunchRequest;
import org.kuali.common.devops.ci.model.Constants;
import org.kuali.common.devops.ci.model.JenkinsContext;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class CreateBuildSlaveAMI {

	private static final Logger logger = Loggers.newLogger();

	private final Stopwatch sw = createStarted();
	private final VirtualSystem vs = VirtualSystem.create();
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), CI_BUILD_SLAVE.getGroup());
	private final Distro distro = Distro.UBUNTU;
	private final String distroVersion = Constants.DISTRO_VERSION;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private final String today = format.format(new Date());
	private final long buildNumber = getBuildNumber();
	private final String startsWithToken = "ec2slave";
	private final Tag name = new ImmutableTag("Name", format("%s.%s-build-%s", startsWithToken, today, buildNumber));
	private static final String amazonAccount = Constants.AMAZON_ACCOUNT;
	public static final KeyPair KUALI_KEY = Auth.getKeyPair(amazonAccount);
	private final int minimumAmisToKeep = 7;
	private final String kisPasswordEncrypted = "lZ7Yxs1+9a9a5di5q1JuiVNnZiNjZN0F";

	private static final Map<String, JenkinsContext> CONTEXTS = getJenkinsContexts();

	private static Map<String, JenkinsContext> getJenkinsContexts() {
		Tags.Name name = Tags.Name.SLAVE;
		JenkinsContext prod = JenkinsContext.builder().withDnsPrefix("ci").withStack(Tags.Stack.PRODUCTION).withName(name).build();
		JenkinsContext test = JenkinsContext.builder().withDnsPrefix("testci").withStack(Tags.Stack.TEST).withName(name).build();
		SortedMap<String, JenkinsContext> contexts = newTreeMap();
		contexts.put("test", test);
		contexts.put("prod", prod);
		return ImmutableMap.copyOf(contexts);
	}

	@Test
	public void test() {
		try {
			VirtualSystem vs = VirtualSystem.create();
			// Default to quiet mode unless they've supplied -Dec2.quiet=false
			boolean quiet = equalsIgnoreCase(vs.getProperties().getProperty("ec2.quiet"), "false") ? false : true;
			JenkinsContext jenkinsContext = SpinUpJenkinsMaster.getJenkinsContext(vs, CONTEXTS);
			// Configurable items
			BasicLaunchRequest request = getSlaveLaunchRequest();
			ProjectIdentifier pid = KUALI_DEVOPS_PROJECT_IDENTIFIER;

			EC2Service service = getEC2Service(amazonAccount);
			List<Tag> tags = getSlaveTags(jenkinsContext);
			Instance instance = launchAndWait(service, request, securityGroups, tags);
			// Instance instance = service.getInstance("i-4423721b");
			service.tag(instance.getInstanceId(), tags);
			logger.info(format("public dns: %s", instance.getPublicDnsName()));
			String dns = instance.getPublicDnsName();
			String privateKey = KUALI_KEY.getPrivateKey().get();
			SpinUpJenkinsMaster.verifySSH(UBUNTU, dns, privateKey);
			SpinUpJenkinsMaster.bootstrap(dns, privateKey);
			SecureChannel channel = SpinUpJenkinsMaster.openSecureChannel(ROOT, dns, privateKey, quiet);
			String basedir = SpinUpJenkinsMaster.publishProject(channel, pid, ROOT, dns, quiet);
			String gpgPassphrase = Auth.decrypt(GPG_PASSPHRASE_ENCRYPTED);
			String quietFlag = (quiet) ? "-q" : "";
			String dnsPrefix = jenkinsContext.getDnsPrefix();
			String jenkinsMaster = Joiner.on('.').join(dnsPrefix, DOMAIN);

			setupEssentials(channel, basedir, pid, distro, distroVersion, gpgPassphrase, dnsPrefix, quietFlag);
			String common = SpinUpJenkinsMaster.getResource(basedir, pid, distro, distroVersion, "jenkins/configurecommon");
			String slave = SpinUpJenkinsMaster.getResource(basedir, pid, distro, distroVersion, "jenkins/configureslave");
			SpinUpJenkinsMaster.exec(channel, common, quietFlag, jenkinsMaster, gpgPassphrase);
			SpinUpJenkinsMaster.exec(channel, slave, quietFlag, jenkinsMaster);
			service.stopInstance(instance.getInstanceId());

			String description = format("automated ec2 slave ami - %s", today);
			List<BlockDeviceMapping> additionalMappings = ImmutableList.<BlockDeviceMapping> of(INSTANCE_STORE_0, INSTANCE_STORE_1);
			CreateAMIRequest creator = CreateAMIRequest.builder().withInstanceId(instance.getInstanceId()).withName(name).withRootVolume(request.getRootVolume())
					.withAdditionalMappings(additionalMappings).withTimeoutMillis(request.getTimeoutMillis()).withDescription(description).build();
			Image image = service.createAmi(creator);
			logger.info(format("created %s - %s", image.getImageId(), FormatUtils.getTime(sw)));
			cleanupAmis(service);
			logger.info(format("terminating instance [%s]", instance.getInstanceId()));
			service.terminateInstance(instance.getInstanceId());
			logger.info(format("updating % with new AMI", jenkinsMaster));
			String kisPassword = Auth.decrypt(kisPasswordEncrypted);
			String ruby = SpinUpJenkinsMaster.getResource(basedir, pid, distro, distroVersion, "jenkins/update_jenkins_1.532.2_ami_headless.rb");
			SpinUpJenkinsMaster.exec(channel, ruby, "jcaddel", kisPassword, jenkinsMaster, image.getImageId());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static void setupEssentials(SecureChannel channel, String basedir, ProjectIdentifier pid, Distro distro, String distroVersion, String gpgPassphrase,
			String dnsPrefix, String quietFlag) {
		String basics = getResource(basedir, pid, distro, distroVersion, "common/configurebasics");
		String ssd = getResource(basedir, pid, distro, distroVersion, "common/configuressd");
		String java = getResource(basedir, pid, distro, distroVersion, "common/installjava");
		exec(channel, basics, quietFlag);
		exec(channel, ssd, quietFlag);
		exec(channel, java, quietFlag, "jdk6", "u45", gpgPassphrase);
		exec(channel, java, quietFlag, "jdk7", "u51", gpgPassphrase);
		exec(channel, java, quietFlag, "jdk8", "u0", gpgPassphrase);
	}

	protected static BasicLaunchRequest getSlaveLaunchRequest() {
		BasicLaunchRequest.Builder builder = BasicLaunchRequest.builder();
		builder.setAmi(Constants.DEFAULT_AMI.getId());
		builder.setTimeoutMillis(FormatUtils.getMillisAsInt("1h"));
		return getBasicLaunchRequest(builder.build());
	}

	protected static BasicLaunchRequest getBasicLaunchRequest(BasicLaunchRequest provided) {
		VirtualSystem vs = VirtualSystem.create();
		Properties system = vs.getProperties();
		String ami = system.getProperty("ec2.ami", provided.getAmi());
		InstanceType type = InstanceType.fromValue(system.getProperty("ec2.type", provided.getType().toString()));
		int size = parseInt(system.getProperty("ec2.size", provided.getRootVolume().getSizeInGigabytes().get() + ""));
		RootVolume rootVolume = RootVolume.create(size, provided.getRootVolume().getDeleteOnTermination().get());
		int timeoutMillis = getMillisAsInt(system.getProperty("ec2.timeout", provided.getTimeoutMillis() + ""));
		return BasicLaunchRequest.builder().withAmi(ami).withRootVolume(rootVolume).withTimeoutMillis(timeoutMillis).withType(type).build();
	}

	protected void cleanupAmis(EC2Service service) {
		List<Image> images = service.getMyImages();
		List<Image> filtered = getFilteredImages(images, name.getKey(), startsWithToken);
		// Most recent images are at the top
		sort(filtered, new ImageTagsComparator());
		List<Image> deletes = newArrayList();
		for (int i = minimumAmisToKeep; i < filtered.size(); i++) {
			deletes.add(filtered.get(i));
		}
		sort(deletes, new ImageTagsComparator());
		logger.info(format("slave ami's ->  total -> %s", filtered.size()));
		logger.info(format("slave ami's -> retain -> %s", minimumAmisToKeep));
		logger.info(format("slave ami's -> delete -> %s", deletes.size()));
		for (Image image : deletes) {
			Tag tag = findRequiredTag(image.getTags(), name.getKey(), startsWithToken);
			logger.info(format("slave ami   -> delete -> %s - [%s]", image.getImageId(), tag.getValue()));
			service.purgeAmi(image.getImageId());
		}

	}

	protected Tag findRequiredTag(List<Tag> tags, String tagKey, String prefix) {
		for (Tag tag : tags) {
			if (tagKey.equals(tag.getKey())) {
				String value = tag.getValue();
				if (value != null && value.startsWith(prefix)) {
					return tag;
				}
			}
		}
		throw illegalState("Unable to locate tag %s", tagKey);
	}

	protected List<Image> getFilteredImages(List<Image> images, String tagKey, String prefix) {
		List<Image> filtered = newArrayList();
		for (Image image : images) {
			List<Tag> tags = image.getTags();
			if (matches(tags, tagKey, prefix)) {
				filtered.add(image);
			}
		}
		return filtered;
	}

	protected boolean matches(List<Tag> tags, String key, String prefix) {
		checkNotNull(tags, "tags");
		checkNotBlank(key, "key");
		checkNotBlank(prefix, "prefix");
		for (Tag tag : tags) {
			if (matches(tag, key, prefix)) {
				return true;
			}
		}
		return false;
	}

	protected boolean matches(Tag tag, String key, String prefix) {
		if (key.equals(tag.getKey())) {
			String value = tag.getValue();
			if (value != null && value.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	protected static List<Tag> getSlaveTags(JenkinsContext context) {
		List<Tag> tags = newArrayList();
		tags.add(context.getName().getTag());
		tags.addAll(getCommonTags(context.getStack().getTag()));
		return ImmutableList.copyOf(tags);
	}

	protected static List<Tag> getCommonTags(Tag stack) {
		List<Tag> tags = newArrayList();
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		tags.add(stack);
		return ImmutableList.copyOf(tags);
	}

	protected static Instance launchAndWait(EC2Service service, BasicLaunchRequest blr, List<KualiSecurityGroup> securityGroups, List<Tag> tags) {
		logger.info(format("launch instance -> %s  type: %s  size: %sgb", blr.getAmi(), blr.getType().toString(), blr.getRootVolume().getSizeInGigabytes().get()));
		List<BlockDeviceMapping> additionalMappings = ImmutableBlockDeviceMapping.DEFAULT_INSTANCE_STORES;
		LaunchInstanceContext context = LaunchInstanceContext.builder(blr.getAmi(), KUALI_KEY).withTimeoutMillis(blr.getTimeoutMillis()).withType(blr.getType())
				.withRootVolume(blr.getRootVolume()).withSecurityGroups(securityGroups).withTags(tags).withAdditionalMappings(additionalMappings).build();
		return service.launchInstance(context);
	}

	protected static EC2Service getEC2Service(String account) {
		AWSCredentials creds = Auth.getAwsCredentials(account);
		WaitService ws = new DefaultWaitService();
		String region = Regions.US_WEST_1.getName();
		EC2ServiceContext ec = new EC2ServiceContext.Builder(creds).withRegionName(Optional.of(region)).build();
		return new DefaultEC2Service(ec, ws);
	}

	/**
	 * If the environment variable BUILD_NUMBER is set, add a prefix and return, otherwise return System.currentTimeMillis()
	 */
	protected long getBuildNumber() {
		Optional<String> buildNumber = fromNullable(vs.getEnvironment().getProperty("BUILD_NUMBER"));
		if (buildNumber.isPresent()) {
			return parseLong(buildNumber.get());
		} else {
			return currentTimeMillis();
		}
	}

	private class ImageTagsComparator implements Comparator<Image> {

		@Override
		public int compare(Image one, Image two) {
			checkNotNull(one.getTags(), "one.tags");
			checkNotNull(two.getTags(), "two.tags");
			String val1 = findRequiredTag(one.getTags(), name.getKey(), startsWithToken).getValue();
			String val2 = findRequiredTag(one.getTags(), name.getKey(), startsWithToken).getValue();
			return val1.compareTo(val2);
		}

	}

}
