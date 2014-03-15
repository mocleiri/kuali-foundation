package org.kuali.common.devops.ci;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.sort;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI_BUILD_SLAVE;
import static org.kuali.common.devops.project.KualiDevOpsProjectConstants.KUALI_DEVOPS_PROJECT_IDENTIFIER;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.base.Threads.sleep;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.devops.project.KualiDevOpsProjectConstants;
import org.kuali.common.util.FormatUtils;
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
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

public class CreateBuildSlaveAMI {

	private static final Logger logger = newLogger();

	private final Stopwatch sw = createStarted();
	private final VirtualSystem vs = VirtualSystem.create();
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), CI_BUILD_SLAVE.getGroup());
	private final List<Tag> tags = getSlaveTags();
	private final String distro = "ubuntu" + vs.getFileSeparator() + "12.04";
	private final String bashScript = "jenkins.sh";
	private final String svnPassword = "PAqzT//IpbTfzhsnLyumedsE7yon7yqi";
	private final String nexusPassword = "/ROzksAX9W5r3CrLMefr9d+C5cIqkDtw";
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private final String today = format.format(new Date());
	private final long buildNumber = getBuildNumber();
	private final String startsWithToken = "ec2slave";
	private final Tag name = new Tag("Name", format("%s.%s-build-%s", startsWithToken, today, buildNumber));
	private static final String amazonAccount = "foundation";
	public static final KeyPair KUALI_KEY = Auth.getKeyPair(amazonAccount);
	private final String domainToken = ".amazonaws.com";
	private final int minimumAmisToKeep = 7;

	@Test
	public void test() {
		// Configurable items
		BasicLaunchRequest request = getSlaveLaunchRequest();
		// The amount of time to sleep after creating a brand new instance (gives DNS a few seconds to figure itself out)
		String sleep = System.getProperty("slave.sleep", "15s");

		EC2Service service = getEC2Service(amazonAccount);
		Instance instance = launchAndWait(service, request, securityGroups, tags);
		// Instance instance = getRunningSlaveInstance(service, "i-1907c23a");
		logger.info(format("public dns: %s", instance.getPublicDnsName()));
		logger.info(format("sleeping %s to let dns settle down", sleep));
		sleep(sleep);
		CanonicalFile buildDir = getBuildDirectory();
		logger.info(format("build directory -> %s", buildDir));
		chmod(buildDir);
		CanonicalFile bashDir = getLocalBashDir(buildDir);
		configureSlave(instance, bashDir);
		String description = format("automated ec2 slave ami - %s", today);
		Image image = service.createAmi(instance.getInstanceId(), name, description, request.getRootVolume(), request.getTimeoutMillis());
		logger.info(format("created %s - %s", image.getImageId(), FormatUtils.getTime(sw)));
		cleanupAmis(service);
		logger.info(format("terminating instance [%s]", instance.getInstanceId()));
		service.terminateInstance(instance.getInstanceId());
	}

	protected static BasicLaunchRequest getSlaveLaunchRequest() {
		BasicLaunchRequest.Builder builder = BasicLaunchRequest.builder();
		builder.setTimeoutMillis(FormatUtils.getMillisAsInt("1h"));
		return getBasicLaunchRequest(builder.build());
	}

	protected static BasicLaunchRequest getBasicLaunchRequest(BasicLaunchRequest provided) {
		String ami = System.getProperty("ec2.ami", provided.getAmi());
		InstanceType type = InstanceType.fromValue(System.getProperty("ec2.type", provided.getType().toString()));
		int size = parseInt(System.getProperty("ec2.size", provided.getRootVolume().getSizeInGigabytes().get() + ""));
		RootVolume rootVolume = RootVolume.create(size, provided.getRootVolume().getDeleteOnTermination().get());
		int timeoutMillis = getMillisAsInt(System.getProperty("ec2.timeout", provided.getTimeoutMillis() + ""));
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

	protected void configureSlave(Instance instance, File bashDir) {
		ExecContext context = getExecContext(instance, bashDir);
		ExecService service = new DefaultExecService();
		service.execute(context);
	}

	protected ExecContext getExecContext(Instance instance, File bashDir) {
		String dns = instance.getPublicDnsName();
		String subdomain = StringUtils.remove(dns, domainToken);
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

	protected static List<Tag> getSlaveTags() {
		List<Tag> tags = newArrayList();
		tags.addAll(getTags());
		tags.add(Tags.Name.SLAVE.getTag());
		return ImmutableList.copyOf(tags);
	}

	protected static List<Tag> getCommonTags(Tag stack) {
		List<Tag> tags = newArrayList();
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		return ImmutableList.copyOf(tags);
	}

	protected static Instance launchAndWait(EC2Service service, BasicLaunchRequest blr, List<KualiSecurityGroup> securityGroups, List<Tag> tags) {
		logger.info(format("launch instance -> %s  type: %s  size: %sgb", blr.getAmi(), blr.getType().toString(), blr.getRootVolume().getSizeInGigabytes().get()));
		LaunchInstanceContext context = LaunchInstanceContext.builder(blr.getAmi(), KUALI_KEY).withTimeoutMillis(blr.getTimeoutMillis()).withType(blr.getType())
				.withRootVolume(blr.getRootVolume()).withSecurityGroups(securityGroups).withTags(tags).build();
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
