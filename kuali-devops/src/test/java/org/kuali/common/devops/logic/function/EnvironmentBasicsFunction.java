package org.kuali.common.devops.logic.function;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;

import org.kuali.common.devops.logic.exec.HttpCacherExecutable;
import org.kuali.common.devops.model.EnvironmentBasics;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ConcurrentExecutables;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

public final class EnvironmentBasicsFunction implements Function<String, EnvironmentBasics> {

	public EnvironmentBasicsFunction() {
		this(true);
	}

	public EnvironmentBasicsFunction(boolean refresh) {
		this.refresh = refresh;
	}

	private final boolean refresh;

	@Override
	public EnvironmentBasics apply(String fqdn) {
		if (refresh) {
			HttpCacherExecutable m = new HttpCacherExecutable(getManifestUrl(fqdn));
			HttpCacherExecutable h = new HttpCacherExecutable(getHeapUrl(fqdn));
			HttpCacherExecutable r = new HttpCacherExecutable(getReleaseNotesUrl(fqdn));
			HttpCacherExecutable e = new HttpCacherExecutable(getEnvJspUrl(fqdn));
			ConcurrentExecutables.execute(ImmutableList.<Executable> of(m, h, r, e));
			FileCache manifest = m.getResult();
			FileCache heap = h.getResult();
			FileCache releaseNotes = r.getResult();
			FileCache environment = e.getResult();
			return EnvironmentBasics.builder().manifest(manifest).heap(heap).releaseNotes(releaseNotes).environment(environment).build();
		} else {
			FileCache manifest = new FileCacheFunction(refresh).apply(getManifestUrl(fqdn));
			FileCache heap = new FileCacheFunction(refresh).apply(getHeapUrl(fqdn));
			FileCache releaseNotes = new FileCacheFunction(refresh).apply(getReleaseNotesUrl(fqdn));
			FileCache environment = new FileCacheFunction(refresh).apply(getEnvJspUrl(fqdn));
			return EnvironmentBasics.builder().manifest(manifest).heap(heap).releaseNotes(releaseNotes).environment(environment).build();
		}
	}

}
