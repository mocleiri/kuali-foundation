package org.kuali.common.devops.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.util.Fqdns;
import org.kuali.common.devops.util.Instances;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();
	private static final String DOMAIN = "kuali.org";

	@Test
	public void test() {
		try {
			Map<String, String> fqdns = Fqdns.getMap();
			Map<String, List<Instance>> instances = Instances.getMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
