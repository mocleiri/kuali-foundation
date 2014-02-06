package org.kuali.common.devops.logic.exec;

import static org.kuali.common.util.base.Assertions.assertNotBlank;

import java.util.List;

import org.kuali.common.devops.logic.Examiner;
import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.logic.Manifests;
import org.kuali.common.devops.logic.Tomcats;
import org.kuali.common.util.execute.Executable;

import com.google.common.collect.Lists;

public final class BasicsCacherExecutable implements Executable {

	public BasicsCacherExecutable(String fqdn) {
		assertNotBlank(fqdn, "fqdn");
		this.fqdn = fqdn;
	}

	private final String fqdn;

	@Override
	public void execute() {
		List<String> urls = Lists.newArrayList();
		urls.add(Manifests.getUrl(fqdn));
		urls.add(Tomcats.getHeapUrl(fqdn));
		urls.add(Tomcats.getReleaseNotesUrl(fqdn));
		urls.add(Examiner.getEnvJspUrl(fqdn));
		HttpCacher.cache(fqdn);
	}

}
