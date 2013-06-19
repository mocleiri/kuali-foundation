package org.kuali.common.aws.spring;

import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;

public class TheTest {

	@Test
	public void test() {
		try {
			Project project = ProjectUtils.loadProject(AwsProjectConfig.GAV);
			Properties mavenProperties = project.getProperties();

			// Default Spring service will do what we need
			SpringService ss = new DefaultSpringService();

			// Setup a Spring context that uses maven properties for placeholder resolution
			SpringContext context = MavenUtils.getMavenizedSpringContext(ss, mavenProperties, AwsMavenPropertySourceConfig.class);

			//
			context.setAnnotatedClasses(CollectionUtils.asList(CloudFrontIndexerConfig.class));

			// Execute Spring
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during test: " + e.getMessage());
		}

	}
}
