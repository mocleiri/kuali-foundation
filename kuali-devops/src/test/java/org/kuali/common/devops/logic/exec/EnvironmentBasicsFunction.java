package org.kuali.common.devops.logic.exec;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;

import org.kuali.common.devops.model.EnvironmentBasics;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ConcurrentExecutables;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

public class EnvironmentBasicsFunction implements Function<String, EnvironmentBasics> {

	@Override
	public EnvironmentBasics apply(String fqdn) {
		HttpCacherExecutable m = new HttpCacherExecutable(getManifestUrl(fqdn));
		HttpCacherExecutable h = new HttpCacherExecutable(getHeapUrl(fqdn));
		HttpCacherExecutable r = new HttpCacherExecutable(getReleaseNotesUrl(fqdn));
		HttpCacherExecutable e = new HttpCacherExecutable(getEnvJspUrl(fqdn));
		ConcurrentExecutables.execute(ImmutableList.<Executable> of(m, h, r, e));
		FileCache manifest = m.getResult();
		FileCache heap = m.getResult();
		FileCache releaseNotes = m.getResult();
		FileCache environment = m.getResult();
		return EnvironmentBasics.builder().manifest(manifest).heap(heap).releaseNotes(releaseNotes).environment(environment).build();
	}

}
