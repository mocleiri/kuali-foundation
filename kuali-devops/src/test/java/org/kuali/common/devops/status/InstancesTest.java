package org.kuali.common.devops.status;

import org.junit.Test;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class InstancesTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			EC2Instance instance = EC2Instance.builder().build();
			logger.info(instance.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
