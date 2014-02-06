package org.kuali.common.devops.logic.exec;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;

import java.io.File;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.model.EnvironmentBasics;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ConcurrentExecutables;

import com.google.common.base.Function;
import com.google.common.base.Optional;
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
			FileCache manifest = getFileCache(fqdn, getManifestUrl(fqdn));
			FileCache heap = getFileCache(fqdn, getHeapUrl(fqdn));
			FileCache releaseNotes = getFileCache(fqdn, getReleaseNotesUrl(fqdn));
			FileCache environment = getFileCache(fqdn, getEnvJspUrl(fqdn));
			return EnvironmentBasics.builder().manifest(manifest).heap(heap).releaseNotes(releaseNotes).environment(environment).build();
		}
	}

	private FileCache getFileCache(String fqdn, String url) {
		File cache = HttpCacher.getCacheFile(url);
		Optional<String> content = getContent(HttpCacher.getCacheFile(url));
		return FileCache.builder().cache(cache).content(content).url(url).build();
	}

	private Optional<String> getContent(File file) {
		if (file.exists()) {
			String content = LocationUtils.toString(file);
			return Optional.of(content);
		} else {
			return Optional.absent();
		}
	}

}
