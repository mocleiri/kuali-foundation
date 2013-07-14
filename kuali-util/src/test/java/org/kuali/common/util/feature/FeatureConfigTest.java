package org.kuali.common.util.feature;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class FeatureConfigTest {

	@Test
	public void test() {

		try {
			System.setProperty("feature.ids", "org.kuali.common:kuali-util:metainf:mpx");
			SpringService ss = new DefaultSpringService();
			ss.load(FeatureTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
