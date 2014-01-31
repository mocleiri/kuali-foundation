package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.devops.aws.Credentials;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Instances {

	private static final Logger logger = LoggerUtils.make();

	private static class InstanceComparator implements Comparator<Instance> {

		@Override
		public int compare(Instance one, Instance two) {
			Tag t1 = getRequiredTag(one, "Name");
			Tag t2 = getRequiredTag(two, "Name");
			checkArgument(t1.getValue().startsWith("env"), "[%s] must start with 'env'", t1.getValue());
			checkArgument(t2.getValue().startsWith("env"), "[%s] must start with 'env'", t2.getValue());
			Integer i1 = Integer.parseInt(t1.getValue().substring(3));
			Integer i2 = Integer.parseInt(t2.getValue().substring(3));
			return Double.compare(i1, i2);
		}

	}

	public static List<AwsRecord> getRecords(Map<String, List<Instance>> instances) {
		List<AwsRecord> records = Lists.newArrayList();
		for (String project : instances.keySet()) {
			List<Instance> envs = instances.get(project);
			for (Instance env : envs) {
				AwsRecord record = getRecord(project, env);
				records.add(record);
			}
		}
		return records;
	}

	protected static AwsRecord getRecord(String project, Instance instance) {
		Tag name = getRequiredTag(instance, "Name");
		AwsRecord record = new AwsRecord();
		record.setDns(instance.getPublicDnsName());
		record.setProject(project);
		record.setType(instance.getInstanceType());
		record.setEnv(name.getValue());
		return record;
	}

	public static Map<String, List<Instance>> getMap() {
		Map<String, AWSCredentials> creds = Auth.getAwsCredentials();
		logger.info(String.format("Using %s sets of AWS credentials", creds.size()));
		WaitService ws = new DefaultWaitService();
		Map<String, List<Instance>> instances = Maps.newTreeMap();
		for (AWSCredentials credentials : creds.values()) {
			String projectName = getProjectName(credentials.getAWSAccessKeyId());
			EC2ServiceContext context = EC2ServiceContext.create(credentials);
			EC2Service service = new DefaultEC2Service(context, ws);
			List<Instance> list = Lists.newArrayList(service.getInstances());
			Iterator<Instance> itr = list.iterator();
			while (itr.hasNext()) {
				Instance i = itr.next();
				if (!service.isOnline(i.getInstanceId())) {
					itr.remove();
				}
			}
			instances.put(projectName, list);
			logger.info(String.format("%s -> %s instances", rightPad(projectName, 12), leftPad(list.size() + "", 2)));
		}
		SortedSet<String> keys = Sets.newTreeSet(instances.keySet());
		for (String key : keys) {
			List<Instance> list = instances.get(key);
			List<Instance> filtered = filter(list);
			sort(filtered, new InstanceComparator());
			logger.info(String.format("%s -> %s environments", rightPad(key, 12), leftPad(filtered.size() + "", 2)));
			instances.put(key, filtered);
		}
		return instances;
	}

	protected static String getProjectName(String accessKey) {
		for (Credentials credentials : Credentials.values()) {
			if (accessKey.equals(credentials.getAWSAccessKeyId())) {
				return credentials.name().toLowerCase();
			}
		}
		throw new IllegalArgumentException(String.format("Unable to locate a name for [%s]", accessKey));
	}

	protected static List<Instance> filter(List<Instance> instances) {
		List<Instance> filtered = Lists.newArrayList();
		for (Instance instance : instances) {
			boolean env = isDeployEnvironment(instance);
			if (env) {
				filtered.add(instance);
			}
		}
		return filtered;
	}

	protected static Tag getRequiredTag(Instance instance, String key) {
		Optional<Tag> optional = getTag(instance, key);
		checkState(optional.isPresent(), "Required tag [%s] is not present for instance [%s]", key, instance.getInstanceId());
		return optional.get();
	}

	protected static Optional<Tag> getTag(Instance instance, String key) {
		List<Tag> tags = instance.getTags();
		for (Tag tag : tags) {
			if (key.equals(tag.getKey())) {
				return Optional.of(tag);
			}
		}
		return Optional.absent();
	}

	protected static boolean isDeployEnvironment(Instance instance) {
		Optional<Tag> tag = getTag(instance, "Name");
		if (!tag.isPresent()) {
			return false;
		}
		String value = tag.get().getValue();
		return value.startsWith("env");
	}

}
