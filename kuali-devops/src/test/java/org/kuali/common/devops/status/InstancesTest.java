package org.kuali.common.devops.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.devops.logic.Instances;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;

public class InstancesTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Map<String, AWSCredentials> map = Auth.getAwsCredentials();
			for (String account : map.keySet()) {
				logger.info(String.format("examining -> %s", account));
				AWSCredentials auth = map.get(account);
				List<EC2Instance> instances = Instances.getInstances(auth);
				logger.info(String.format("%s -> %s", account, instances.size()));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
