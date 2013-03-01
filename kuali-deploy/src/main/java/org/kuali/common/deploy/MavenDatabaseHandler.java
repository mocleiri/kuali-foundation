package org.kuali.common.deploy;

import org.kuali.common.util.service.DefaultMavenService;
import org.kuali.common.util.service.MavenContext;
import org.kuali.common.util.service.MavenService;
import org.springframework.util.Assert;

public class MavenDatabaseHandler implements DatabaseHandler {

	MavenService service = new DefaultMavenService();
	MavenContext context;

	@Override
	public void reset() {
		Assert.notNull(service);
		Assert.notNull(context);
		service.execute(context);
	}

}
