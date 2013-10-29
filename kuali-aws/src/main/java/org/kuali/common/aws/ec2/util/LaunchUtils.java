package org.kuali.common.aws.ec2.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.util.Str;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.util.Assert;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class LaunchUtils {

	private static final String AMI_KEY = "ec2.ami";
	private static final String KEY_NAME_KEY = "ec2.keyName";
	private static final String TYPE_KEY = "ec2.type";
	private static final String SECURITY_GROUPS_KEY = "ec2.securityGroups";
	private static final String TAGS_KEY = "ec2.tags";
	private static final String AVAILABILITY_ZONE_KEY = "ec2.availabilityZone";
	private static final String LAUNCH_TIMEOUT_KEY = "ec2.launchTimeout";
	private static final String PREVENT_TERMINATION_KEY = "ec2.preventTermination";
	private static final String EBS_OPTIMIZED_KEY = "ec2.ebsOptimized";
	private static final String ENABLE_MONITORING_KEY = "ec2.enableMonitoring";
	private static final String ROOT_VOLUME_SIZE_KEY = "ec2.rootVolume.sizeInGigabytes";
	private static final String ROOT_VOLUME_DELETE_KEY = "ec2.rootVolume.deleteOnTermination";
	private static final LaunchInstanceContext DEFAULT_CONTEXT = new LaunchInstanceContext.Builder(NullUtils.NONE, NullUtils.NONE).build();

	public static InstanceType getType(EnvironmentService env, InstanceType type) {
		return InstanceType.fromValue(env.getString(TYPE_KEY, type.toString()));
	}

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env, LaunchInstanceContext provided) {
		String ami = NullUtils.trimToNull(env.getString(AMI_KEY, provided.getAmi()));
		String keyName = NullUtils.trimToNull(env.getString(KEY_NAME_KEY, provided.getKeyName()));
		InstanceType type = getType(env, provided.getType());
		int timeoutMillis = SpringUtils.getMillisAsInt(env, LAUNCH_TIMEOUT_KEY, provided.getTimeoutMillis());
		boolean ebsOptimized = env.getBoolean(EBS_OPTIMIZED_KEY, provided.isEbsOptimized());
		boolean enableMonitoring = env.getBoolean(ENABLE_MONITORING_KEY, provided.isEnableMonitoring());
		boolean preventTermination = env.getBoolean(PREVENT_TERMINATION_KEY, provided.isPreventTermination());

		// TODO
		Optional<RootVolume> rootVolume = getRootVolume(env, provided.getRootVolume());
		Optional<String> availabilityZone = SpringUtils.getOptionalString(env, AVAILABILITY_ZONE_KEY);
		List<Tag> tags = getTags(env);
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, SECURITY_GROUPS_KEY);

		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone.get()).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume.orNull()).timeoutMillis(timeoutMillis).ebsOptimized(ebsOptimized).enableMonitoring(enableMonitoring)
				.build();
	}

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env) {
		return getLaunchInstanceContext(env, DEFAULT_CONTEXT);
	}

	public static Optional<RootVolume> getRootVolume(EnvironmentService env, Optional<RootVolume> provided) {
		Optional<Integer> sizeInGigabytes = getSizeInGigaBytes(env, provided);
		Optional<Boolean> deleteOnTermination = getDeleteOnTermination(env, provided);
		if (deleteOnTermination.isPresent()) {
			return Optional.of(new RootVolume(sizeInGigabytes, deleteOnTermination.get()));
		} else if (sizeInGigabytes.isPresent()) {
			return Optional.of(new RootVolume(sizeInGigabytes.get()));
		} else {
			return Optional.absent();
		}
	}

	protected static Optional<Boolean> getDeleteOnTermination(EnvironmentService env, Optional<RootVolume> provided) {
		if (env.containsProperty(ROOT_VOLUME_DELETE_KEY)) {
			return SpringUtils.getOptionalBoolean(env, ROOT_VOLUME_SIZE_KEY);
		} else {
			return provided.isPresent() ? Optional.of(provided.get().isDeleteOnTermination()) : Optional.<Boolean> absent();
		}
	}

	protected static Optional<Integer> getSizeInGigaBytes(EnvironmentService env, Optional<RootVolume> provided) {
		if (env.containsProperty(ROOT_VOLUME_SIZE_KEY)) {
			return SpringUtils.getOptionalInteger(env, ROOT_VOLUME_SIZE_KEY);
		} else {
			return provided.isPresent() ? provided.get().getSizeInGigabytes() : Optional.<Integer> absent();
		}
	}

	public static List<Tag> getTags(EnvironmentService env, List<Tag> provided) {
		if (env.containsProperty(TAGS_KEY)) {
			return getTags(env);
		} else {
			return provided;
		}
	}

	public static List<Tag> getTags(EnvironmentService env) {
		List<String> list = SpringUtils.getNoneSensitiveListFromCSV(env, TAGS_KEY, NullUtils.NONE);
		List<Tag> tags = new ArrayList<Tag>();
		for (String element : list) {
			String[] tokens = Str.splitAndTrim(element, "=");
			Assert.isTrue(tokens.length == 2, "Expected exactly 2 tokens");
			String key = tokens[0];
			String value = tokens[1];
			Tag tag = new Tag(key, value);
			tags.add(tag);
		}
		return ImmutableList.copyOf(tags);
	}
}
