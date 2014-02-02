package org.kuali.common.devops.logic;

import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class Instances {

	private static final Logger logger = LoggerUtils.make();

	public static List<EC2Instance> getInstances(AWSCredentials creds) {
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext context = EC2ServiceContext.create(creds);
		EC2Service service = new DefaultEC2Service(context, ws);
		List<Instance> list = Lists.newArrayList(service.getInstances());
		List<EC2Instance> instances = Lists.newArrayList();
		return instances;
	}

	protected List<EC2Instance> convert(List<Instance> instances) {
		List<EC2Instance> list = Lists.newArrayList();
		for (Instance instance : instances) {
			list.add(convert(instance));
		}
		return list;
	}

	protected EC2Instance convert(Instance instance) {
		String id = instance.getInstanceId();
		Optional<String> name = getName(instance);
		String publicDnsName = instance.getPublicDnsName();
		String type = instance.getInstanceType();
		long launchTime = instance.getLaunchTime().getTime();
		return EC2Instance.builder().id(id).name(name).publicDnsName(publicDnsName).type(type).launchTime(launchTime).build();
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

}
