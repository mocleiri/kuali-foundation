package org.kuali.common.devops.status;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.leftPad;

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
				AWSCredentials auth = map.get(account);
				List<EC2Instance> instances = Instances.getInstances(account, auth);
				logger.info(format("%s -> %s", rightPad(account, 10), leftPad(instances.size() + "", 2)));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
