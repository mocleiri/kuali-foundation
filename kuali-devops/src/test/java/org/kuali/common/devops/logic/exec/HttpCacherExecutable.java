package org.kuali.common.devops.logic.exec;

import static org.kuali.common.util.base.Assertions.assertNotBlank;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.util.execute.Executable;

public final class HttpCacherExecutable implements Executable {

	public HttpCacherExecutable(String url) {
		assertNotBlank(url, "url");
		this.url = url;
	}

	private final String url;

	@Override
	public void execute() {
		HttpCacher.cache(url);
	}

}
