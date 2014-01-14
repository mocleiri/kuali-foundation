package org.kuali.common.deploy;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

/**
 * @deprecated
 */
@Deprecated
public class DeployTest {

	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			System.setProperty("properties.decrypt", "true");
			System.setProperty("deploy.env", "16");
			System.setProperty("kdo.db.skip", "true");
			ss.load(KSDeployConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
