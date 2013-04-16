package org.kuali.common.impex.spring;

import org.junit.Test;
import org.kuali.common.jdbc.spring.ResetConfig;
import org.kuali.common.jdbc.spring.ResetController;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;

public class KSResetTest {

	@Test
	public void test() {
		try {
			// Default Spring service will do what we need
			SpringService ss = new DefaultSpringService();

			// Setup a Spring context that uses maven properties for placeholder resolution
			SpringContext context = MavenUtils.getMavenizedSpringContext(ss, KSPropertiesConfig.KS_MAVEN_PROPS, KSMavenPropertySourceConfig.class);

			// Reset the db using annotated config
			context.setAnnotatedClasses(CollectionUtils.asList(ResetConfig.class, MpxSupplierConfig.class, ResetController.class));

			// Execute Spring
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
