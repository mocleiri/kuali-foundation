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

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env, LaunchInstanceContext context) {
		String ami = NullUtils.trimToNull(env.getString(AMI_KEY, context.getAmi()));
		String keyName = NullUtils.trimToNull(env.getString(KEY_NAME_KEY, context.getKeyName()));
		InstanceType type = getType(env, context.getType());
		int timeoutMillis = SpringUtils.getMillisAsInt(env, LAUNCH_TIMEOUT_KEY, context.getTimeoutMillis());
		boolean ebsOptimized = env.getBoolean(EBS_OPTIMIZED_KEY, context.isEbsOptimized());
		boolean enableMonitoring = env.getBoolean(ENABLE_MONITORING_KEY, context.isEnableMonitoring());
		boolean preventTermination = env.getBoolean(PREVENT_TERMINATION_KEY, context.isPreventTermination());

		// TODO
		RootVolume rootVolume = getRootVolume(env);
		Optional<String> availabilityZone = SpringUtils.getOptionalString(env, AVAILABILITY_ZONE_KEY);
		List<Tag> tags = getTags(env);
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, SECURITY_GROUPS_KEY);

		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone.get()).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume).timeoutMillis(timeoutMillis).ebsOptimized(ebsOptimized).enableMonitoring(enableMonitoring).build();
	}

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env) {
		return getLaunchInstanceContext(env, DEFAULT_CONTEXT);
	}

	public static RootVolume getRootVolume(EnvironmentService env, RootVolume rootVolume) {
		Optional<Integer> sizeInGigabytes = SpringUtils.getOptionalInteger(env, ROOT_VOLUME_SIZE_KEY);
		boolean deleteOnTermination = env.getBoolean(ROOT_VOLUME_DELETE_KEY, RootVolume.DEFAULT_DELETE_ON_TERMINATION);
		return new RootVolume(sizeInGigabytes, deleteOnTermination);
	}

	public static RootVolume getRootVolume(EnvironmentService env) {
		Optional<Integer> sizeInGigabytes = SpringUtils.getOptionalInteger(env, ROOT_VOLUME_SIZE_KEY);
		boolean deleteOnTermination = env.getBoolean(ROOT_VOLUME_DELETE_KEY, RootVolume.DEFAULT_DELETE_ON_TERMINATION);
		return new RootVolume(sizeInGigabytes, deleteOnTermination);
	}

	public static List<Tag> getTags(EnvironmentService env, List<Tag> defaults) {
		if (env.containsProperty(TAGS_KEY)) {
			return getTags(env);
		} else {
			return defaults;
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
