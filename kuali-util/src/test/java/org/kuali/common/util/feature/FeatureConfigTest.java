package org.kuali.common.util.feature;

import org.junit.Test;
import org.kuali.common.util.feature.spring.FeatureConfig;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class FeatureConfigTest {

	@Test
	public void test() {

		try {
			System.setProperty("feature.ids", "org.kuali.common:kuali-util:metainf");
			SpringService ss = new DefaultSpringService();
			ss.load(FeatureConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
