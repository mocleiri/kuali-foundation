package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.apache.commons.lang.StringUtils.trimToNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Instances {

	private static final File CACHE_DIR = new CanonicalFile("./target/aws/ec2");
	private static final String ABSENT = "${optional.absent}";

	public static Map<String, List<EC2Instance>> getInstances(boolean refresh) {
		Map<String, List<EC2Instance>> map = Maps.newTreeMap();
		Map<String, AWSCredentials> credentials = Auth.getAwsCredentials();
		for (String account : credentials.keySet()) {
			List<EC2Instance> instances = getInstances(account, credentials.get(account), refresh);
			map.put(account, instances);
		}
		return map;
	}

	protected static List<EC2Instance> getInstances(String account, AWSCredentials creds, boolean refresh) {
		File file = new CanonicalFile(CACHE_DIR, account + ".txt");
		if (!file.exists() || refresh) {
			List<EC2Instance> instances = queryAmazon(creds);
			store(file, instances);
			return instances;
		} else {
			return load(file);
		}
	}

	protected static List<EC2Instance> load(File file) {
		try {
			List<String> lines = FileUtils.readLines(file);
			List<EC2Instance> instances = Lists.newArrayList();
			for (int i = 1; i < lines.size(); i++) {
				instances.add(convert(lines.get(i)));
			}
			return instances;
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> %s", file);
		}
	}

	protected static EC2Instance convert(String csv) {
		List<String> tokens = Splitter.on(',').splitToList(csv);
		String id = tokens.get(0);
		String ami = tokens.get(1);
		long launchTime = Long.parseLong(tokens.get(2));
		Optional<String> name = getOptional(tokens.get(3));
		String state = tokens.get(4);
		String type = tokens.get(5);
		Optional<String> publicDnsName = getOptional(tokens.get(6));
		return EC2Instance.builder().id(id).ami(ami).launchTime(launchTime).name(name).state(state).type(type).publicDnsName(publicDnsName).build();
	}

	protected static Optional<String> getOptional(String string) {
		if (string.equals(ABSENT)) {
			return Optional.absent();
		} else {
			return Optional.of(string);
		}
	}

	protected static List<EC2Instance> queryAmazon(AWSCredentials creds) {
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext context = EC2ServiceContext.create(creds);
		EC2Service service = new DefaultEC2Service(context, ws);
		List<EC2Instance> instances = convert(service.getInstances());
		Collections.sort(instances);
		return instances;
	}

	protected static void store(File file, List<EC2Instance> instances) {
		List<String> csv = csv(instances);
		try {
			writeLines(file, csv);
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> [%s]", file);
		}
	}

	protected static List<String> csv(List<EC2Instance> instances) {
		List<String> lines = Lists.newArrayList();
		lines.add("id,ami,launchTime,name,state,type,publicDnsName");
		for (EC2Instance instance : instances) {
			lines.add(csv(instance));
		}
		return lines;
	}

	protected static String csv(EC2Instance instance) {
		List<String> tokens = Lists.newArrayList();
		tokens.add(instance.getId());
		tokens.add(instance.getAmi());
		tokens.add(Long.toString(instance.getLaunchTime()));
		tokens.add(toString(instance.getName()));
		tokens.add(instance.getState());
		tokens.add(instance.getType());
		tokens.add(toString(instance.getPublicDnsName()));
		return Joiner.on(',').join(tokens);
	}

	protected static List<EC2Instance> convert(List<Instance> instances) {
		List<EC2Instance> list = Lists.newArrayList();
		for (Instance instance : instances) {
			list.add(convert(instance));
		}
		return list;
	}

	protected static EC2Instance convert(Instance instance) {
		String id = instance.getInstanceId();
		Optional<String> name = getName(instance);
		Optional<String> publicDnsName = fromNullable(trimToNull(instance.getPublicDnsName()));
		String type = instance.getInstanceType();
		long launchTime = instance.getLaunchTime().getTime();
		String ami = instance.getImageId();
		String state = instance.getState().getName();
		return EC2Instance.builder().id(id).name(name).publicDnsName(publicDnsName).type(type).launchTime(launchTime).ami(ami).state(state).build();
	}

	protected static Optional<String> getName(Instance instance) {
		Optional<Tag> name = getTag(instance, "Name");
		if (name.isPresent()) {
			return Optional.of(name.get().getValue());
		} else {
			return Optional.absent();
		}
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

	protected static String toString(Optional<String> string) {
		if (string.isPresent()) {
			return string.get();
		} else {
			return ABSENT;
		}
	}

}
