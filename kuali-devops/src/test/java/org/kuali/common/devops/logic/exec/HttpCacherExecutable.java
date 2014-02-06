package org.kuali.common.devops.logic.exec;

import static org.kuali.common.util.base.Assertions.assertNotBlank;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.execute.Executable;

public final class HttpCacherExecutable implements Executable {

	public HttpCacherExecutable(String url) {
		this.url = assertNotBlank(url, "url");
	}

	private final String url;

	private FileCache result;

	@Override
	public void execute() {
		this.result = HttpCacher.refresh(url);
	}

	public FileCache getResult() {
		return result;
	}

}
