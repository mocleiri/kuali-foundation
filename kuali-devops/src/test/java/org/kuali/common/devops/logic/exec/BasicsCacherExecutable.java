package org.kuali.common.devops.logic.exec;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;
import static org.kuali.common.util.base.Assertions.assertNotBlank;

import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ConcurrentExecutables;

import com.google.common.collect.ImmutableList;

public final class BasicsCacherExecutable implements Executable {

	public BasicsCacherExecutable(String fqdn) {
		this.fqdn = assertNotBlank(fqdn, "fqdn");
	}

	private final String fqdn;

	@Override
	public void execute() {
		HttpCacherExecutable m = new HttpCacherExecutable(getManifestUrl(fqdn));
		HttpCacherExecutable h = new HttpCacherExecutable(getHeapUrl(fqdn));
		HttpCacherExecutable r = new HttpCacherExecutable(getReleaseNotesUrl(fqdn));
		HttpCacherExecutable e = new HttpCacherExecutable(getEnvJspUrl(fqdn));
		ConcurrentExecutables.execute(ImmutableList.<Executable> of(m, h, r, e));
		FileCache manifest = m.getResult();
		FileCache heap = m.getResult();
		FileCache releaseNotes = m.getResult();
		FileCache environment = m.getResult();
	}

}
