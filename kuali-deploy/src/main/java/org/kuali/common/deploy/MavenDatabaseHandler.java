package org.kuali.common.deploy;

import org.kuali.common.util.service.DefaultMavenService;
import org.kuali.common.util.service.MavenContext;
import org.kuali.common.util.service.MavenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class MavenDatabaseHandler implements DatabaseHandler {

	private static final Logger logger = LoggerFactory.getLogger(MavenDatabaseHandler.class);

	MavenService service = new DefaultMavenService();
	MavenContext context;
	boolean skip = true;

	@Override
	public void reset() {
		if (skip) {
			logger.info("Skipping database reset");
			return;
		}
		Assert.notNull(service);
		Assert.notNull(context);
		service.execute(context);
	}

	public MavenService getService() {
		return service;
	}

	public void setService(MavenService service) {
		this.service = service;
	}

	public MavenContext getContext() {
		return context;
	}

	public void setContext(MavenContext context) {
		this.context = context;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
