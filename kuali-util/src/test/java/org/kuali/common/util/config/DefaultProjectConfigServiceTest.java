package org.kuali.common.util.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DefaultProjectConfigServiceTest {

	@Test
	public void test() {

		try {
			ContextConfig mpx = new ContextConfig("mpx", Arrays.asList(new Location("${metainf.common}/mpx.properties")));
			ContextConfig sql = new ContextConfig("sql", Arrays.asList(new Location("${metainf.common}/sql.properties")));
			ContextConfig metainf = new ContextConfig("metainf");
			metainf.setChildren(Arrays.asList(mpx, sql));
			List<ContextConfig> contexts = new ArrayList<ContextConfig>();
			contexts.add(metainf);

			List<Location> locations = new ArrayList<Location>();
			locations.add(new Location("${classpath.prefix}/sql.xml"));
			ProjectConfig config = new ProjectConfig();
			config.setGroupId("org.kuali.common");
			config.setArtifactId("kuali-util");
			config.setLocations(locations);
			config.setContexts(contexts);
			DefaultProjectConfigService service = new DefaultProjectConfigService();
			File file = new File("/tmp/metadata.xml");
			service.store(file, config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
