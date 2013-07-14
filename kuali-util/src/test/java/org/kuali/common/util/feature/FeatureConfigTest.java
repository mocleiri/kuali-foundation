package org.kuali.common.util.feature;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class FeatureConfigTest {

	@Test
	public void test() {

		try {
			List<String> ids = new ArrayList<String>();
			ids.add("org.kuali.common:kuali-util:metainf:mpx");
			ids.add("org.kuali.common:kuali-util:metainf:sql");
			ids.add("org.kuali.common:kuali-util:metainf");
			ids.add("org.kuali.common:kuali-util:scm");
			System.setProperty("feature.ids", CollectionUtils.getCSV(ids));
			SpringService ss = new DefaultSpringService();
			ss.load(FeatureTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
