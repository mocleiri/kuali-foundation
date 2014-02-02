package org.kuali.common.devops.status;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.logic.Instances;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.validate.Validation;
import org.slf4j.Logger;

public class InstancesTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			Validation.getDefaultValidator();
			Map<String, List<EC2Instance>> map = Instances.getInstances(true);
			for (String account : map.keySet()) {
				List<EC2Instance> instances = map.get(account);
				logger.info(format("%s -> %s", rightPad(account, 10), leftPad(instances.size() + "", 2)));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
