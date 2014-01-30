package org.kuali.common.devops.util;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
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

public class Instances {

	private static final Logger logger = LoggerUtils.make();

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
		List<AWSCredentials> creds = Auth.getCredentials();
		logger.info(String.format("Using %s sets of AWS credentials", creds.size()));
		WaitService ws = new DefaultWaitService();
		Map<String, List<Instance>> instances = Maps.newHashMap();
		for (AWSCredentials credentials : creds) {
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
			String projectName = getProjectName(credentials.getAWSAccessKeyId());
			instances.put(projectName, list);
			logger.debug(String.format("Located %s instances for %s", list.size(), projectName));
		}
		for (String key : instances.keySet()) {
			List<Instance> list = instances.get(key);
			List<Instance> filtered = filter(list);
			logger.info(String.format("%s -> %s environments", StringUtils.rightPad(key, 12), filtered.size()));
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
