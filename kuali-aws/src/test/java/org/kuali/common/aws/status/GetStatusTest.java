package org.kuali.common.aws.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class GetStatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Map<String, List<Instance>> map = getMap();
			for (String key : map.keySet()) {
				List<Instance> instances = map.get(key);
				List<Instance> filtered = filter(instances);
				logger.info(String.format("Located %s instances hosting deployed environments", filtered.size()));
				map.put(key, filtered);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	protected boolean isDeployEnvironment(Instance instance) {
		List<Tag> tags = instance.getTags();
		for (Tag tag : tags) {
			String key = tag.getKey();
			if (key.equals("Name")) {
				return tag.getValue().startsWith("env");
			}
		}
		return false;
	}

	protected Map<String, List<Instance>> getMap() {
		List<AWSCredentials> creds = Auth.getCredentials();
		logger.info(String.format("Located %s sets of credentials", creds.size()));
		WaitService ws = new DefaultWaitService();
		Map<String, List<Instance>> instances = Maps.newHashMap();
		for (AWSCredentials credentials : creds) {
			EC2ServiceContext context = EC2ServiceContext.create(credentials);
			EC2Service service = new DefaultEC2Service(context, ws);
			List<Instance> list = service.getInstances();
			instances.put(credentials.getAWSAccessKeyId(), list);
			logger.info(String.format("Located %s instances for %s", list.size(), credentials.getAWSAccessKeyId()));
		}
		return instances;
	}
}
