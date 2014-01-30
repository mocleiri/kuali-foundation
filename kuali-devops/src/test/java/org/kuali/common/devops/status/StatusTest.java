package org.kuali.common.devops.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.util.AwsRecord;
import org.kuali.common.devops.util.Instances;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			// Map<String, String> fqdns = Fqdns.getMap();
			Map<String, List<Instance>> instances = Instances.getMap();
			List<AwsRecord> records = Instances.getRecords(instances);
			logger.info("Located {} records", records.size());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
