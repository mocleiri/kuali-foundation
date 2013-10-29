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

	public static final String AMI_KEY = "ec2.ami";
	public static final String KEY_NAME_KEY = "ec2.keyName";
	public static final String AVAILABILITY_ZONE_KEY = "ec2.availabilityZone";
	public static final String TYPE_KEY = "ec2.type";
	public static final String SECURITY_GROUPS_KEY = "ec2.securityGroups";
	public static final String PREVENT_TERMINATION_KEY = "ec2.preventTermination";
	public static final String TAGS_KEY = "ec2.tags";
	public static final String ROOT_VOLUME_SIZE_KEY = "ec2.rootVolume.sizeInGigabytes";
	public static final String ROOT_VOLUME_DELETE_KEY = "ec2.rootVolume.deleteOnTermination";

	public static LaunchInstanceContext getLaunchInstanceContext(EnvironmentService env) {
		String ami = env.getString(AMI_KEY);
		String keyName = env.getString(KEY_NAME_KEY);
		String availabilityZone = env.getString(AVAILABILITY_ZONE_KEY, NullUtils.NONE);
		InstanceType type = InstanceType.fromValue(env.getString(TYPE_KEY));
		List<Tag> tags = getTags(env);
		List<String> securityGroups = SpringUtils.getNoneSensitiveListFromCSV(env, SECURITY_GROUPS_KEY);
		RootVolume rootVolume = getRootVolume(env);
		boolean preventTermination = env.getBoolean(PREVENT_TERMINATION_KEY, false);
		return new LaunchInstanceContext.Builder(ami, keyName).type(type).availabilityZone(availabilityZone).tags(tags).securityGroups(securityGroups)
				.preventTermination(preventTermination).rootVolume(rootVolume).build();
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
