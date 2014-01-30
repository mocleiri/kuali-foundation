package org.kuali.common.devops.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.util.Fqdns;
import org.kuali.common.devops.util.Instances;

import com.amazonaws.services.ec2.model.Instance;

public class StatusTest {

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
