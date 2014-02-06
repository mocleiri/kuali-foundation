package org.kuali.common.devops.logic.exec;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;
import static org.kuali.common.util.base.Assertions.assertNotBlank;

import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ConcurrentExecutables;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class BasicsCacherExecutable implements Executable {

	public BasicsCacherExecutable(String fqdn) {
		assertNotBlank(fqdn, "fqdn");
		this.fqdn = fqdn;
	}

	private final String fqdn;

	@Override
	public void execute() {
		List<String> urls = ImmutableList.of(getManifestUrl(fqdn), getHeapUrl(fqdn), getReleaseNotesUrl(fqdn), getEnvJspUrl(fqdn));
		List<Executable> executables = Lists.newArrayList();
		for (String url : urls) {
			executables.add(new HttpCacherExecutable(url));
		}
		ConcurrentExecutables.builder(executables).build().execute();
	}

}
