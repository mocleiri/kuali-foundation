package org.kuali.common.aws.status;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class MergeTest {

	private static final Logger logger = LoggerUtils.make();
	private static final Joiner JOINER = Joiner.on(',');
	private static final Joiner LINES = Joiner.on('\n');

	@Test
	public void test() {
		try {
			Map<String, List<Instance>> map = getMap();
			List<String> lines = getLines(map);
			File file = new CanonicalFile("./target/env/aws.csv");
			FileUtils.write(file, LINES.join(lines));
			logger.info(String.format("created -> %s", file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<String> getLines(Map<String, List<Instance>> map) {
		SortedSet<String> projects = Sets.newTreeSet(map.keySet());
		List<String> lines = Lists.newArrayList();
		for (String project : projects) {
			List<Instance> instances = map.get(project);
			lines.addAll(getLines(project, instances));
		}
		return lines;
	}

	protected List<String> getLines(String project, List<Instance> instances) {
		Collections.sort(instances, new InstanceComparator());
		List<String> lines = Lists.newArrayList();
		for (Instance instance : instances) {
			lines.add(getLine(project, instance));
		}
		return lines;
	}

	protected String getLine(String project, Instance instance) {
		Tag name = getRequiredTag(instance, "Name");
		List<String> tokens = Lists.newArrayList();
		tokens.add(project);
		tokens.add(name.getValue());
		tokens.add(instance.getPublicDnsName());
		tokens.add(instance.getInstanceType());
		return JOINER.join(tokens);
	}

	private class InstanceComparator implements Comparator<Instance> {

		@Override
		public int compare(Instance one, Instance two) {
			Tag t1 = getRequiredTag(one, "Name");
			Tag t2 = getRequiredTag(two, "Name");
			Integer i1 = Integer.parseInt(t1.getValue().substring(3));
			Integer i2 = Integer.parseInt(t2.getValue().substring(3));
			return Double.compare(i1, i2);
		}

	}

	protected List<Instance> filter(List<Instance> instances) {
		List<Instance> filtered = Lists.newArrayList();
		for (Instance instance : instances) {
			if (isDeployEnvironment(instance)) {
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

	protected boolean isDeployEnvironment(Instance instance) {
		Optional<Tag> tag = getTag(instance, "Name");
		if (!tag.isPresent()) {
			return false;
		}
		String value = tag.get().getValue();
		return value.startsWith("env");
	}

	protected String getProjectName(String accessKey) {
		for (Credentials credentials : Credentials.values()) {
			if (accessKey.equals(credentials.getAWSAccessKeyId())) {
				return credentials.name().toLowerCase();
			}
		}
		throw new IllegalArgumentException(String.format("Unable to locate a name for [%s]", accessKey));
	}

	protected Map<String, List<Instance>> getMap() {
		List<AWSCredentials> creds = Auth.getCredentials();
		logger.info(String.format("Using %s sets of AWS credentials", creds.size()));
		WaitService ws = new DefaultWaitService();
		Map<String, List<Instance>> instances = Maps.newHashMap();
		for (AWSCredentials credentials : creds) {
			EC2ServiceContext context = EC2ServiceContext.create(credentials);
			EC2Service service = new DefaultEC2Service(context, ws);
			List<Instance> list = service.getInstances();
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
}
