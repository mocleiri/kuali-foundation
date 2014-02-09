package org.kuali.common.devops.status;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.format;

import java.util.List;

import org.junit.Test;
import org.kuali.common.devops.metadata.logic.DefaultEnvironmentMetadataService;
import org.kuali.common.devops.metadata.logic.EnvironmentMetadataService;
import org.kuali.common.devops.metadata.model.EnvironmentMetadata;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Stopwatch;

public class MetadataServiceTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			// List<String> fqdns = of("env1.rice.kuali.org", "env2.rice.kuali.org", "env1.ks.kuali.org", "dev.ole.kuali.org", "dev.docstore.ole.kuali.org", "env3.rice.kuali.org");
			List<String> fqdns = of("env3.rice.kuali.org");
			EnvironmentMetadataService service = new DefaultEnvironmentMetadataService();
			Stopwatch sw = createStarted();
			List<EnvironmentMetadata> metadata = service.getMetadata(fqdns);
			logger.info(format("fqdns: %s, elapsed -> %s", metadata.size(), FormatUtils.getTime(sw)));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
