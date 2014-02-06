package org.kuali.common.devops.logic.exec;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;
import static org.kuali.common.util.base.Assertions.assertNotBlank;

import java.util.List;

import org.kuali.common.devops.logic.function.ConcurrentFunctions;
import org.kuali.common.devops.logic.function.FileCacheFunction;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.execute.Executable;

import com.google.common.collect.ImmutableList;

public final class BasicsCacherExecutable implements Executable {

	public BasicsCacherExecutable(String fqdn) {
		this.fqdn = assertNotBlank(fqdn, "fqdn");
	}

	private final String fqdn;

	@Override
	public void execute() {
		List<String> urls = ImmutableList.of(getManifestUrl(fqdn), getHeapUrl(fqdn), getReleaseNotesUrl(fqdn), getEnvJspUrl(fqdn));
		FileCacheFunction function = new FileCacheFunction();
		ConcurrentFunctions<String, FileCache> cf = new ConcurrentFunctions<String, FileCache>(function, urls);
		List<FileCache> results = cf.apply();
	}

}
