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

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env) {
		String ami = env.getString(AMI_KEY);
		String keyName = env.getString(KEY_NAME_KEY);
		Optional<String> availabilityZone = SpringUtils.getOptionalString(env, AVAILABILITY_ZONE_KEY);
		InstanceType type = InstanceType.fromValue(env.getString(TYPE_KEY, LaunchInstanceContext.DEFAULT_INSTANCE_TYPE.toString()));
		int timeoutMillis = SpringUtils.getMillisAsInt(env, LAUNCH_TIMEOUT_KEY, LaunchInstanceContext.DEFAULT_TIMEOUT_MILLIS_STRING);
		boolean ebsOptimized = env.getBoolean(EBS_OPTIMIZED_KEY, LaunchInstanceContext.DEFAULT_EBS_OPTIMIZED);
		boolean enableMonitoring = env.getBoolean(ENABLE_MONITORING_KEY, LaunchInstanceContext.DEFAULT_ENABLE_MONITORING);
		List<Tag> tags = getTags(env);
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, SECURITY_GROUPS_KEY);
		RootVolume rootVolume = getRootVolume(env);
		boolean preventTermination = env.getBoolean(PREVENT_TERMINATION_KEY, LaunchInstanceContext.DEFAULT_PREVENT_TERMINATION);
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone.get()).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume).timeoutMillis(timeoutMillis).ebsOptimized(ebsOptimized).enableMonitoring(enableMonitoring).build();
	}

	public static RootVolume getRootVolume(EnvironmentService env) {
		Optional<Integer> sizeInGigabytes = SpringUtils.getOptionalInteger(env, ROOT_VOLUME_SIZE_KEY);
		boolean deleteOnTermination = env.getBoolean(ROOT_VOLUME_DELETE_KEY, RootVolume.DEFAULT_DELETE_ON_TERMINATION);
		return new RootVolume(sizeInGigabytes, deleteOnTermination);
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
