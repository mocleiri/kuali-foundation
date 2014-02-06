package org.kuali.common.devops.logic.function;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;

import org.kuali.common.devops.logic.exec.FunctionExecutable;
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
		Function<String, FileCache> function = new FileCacheFunction(refresh);
		FunctionExecutable<String, FileCache> manifest = FunctionExecutable.create(function, getManifestUrl(fqdn));
		FunctionExecutable<String, FileCache> heap = FunctionExecutable.create(function, getHeapUrl(fqdn));
		FunctionExecutable<String, FileCache> releaseNotes = FunctionExecutable.create(function, getReleaseNotesUrl(fqdn));
		FunctionExecutable<String, FileCache> environment = FunctionExecutable.create(function, getEnvJspUrl(fqdn));
		ConcurrentExecutables.execute(ImmutableList.<Executable> of(manifest, heap, releaseNotes, environment));
		EnvironmentBasics.Builder builder = EnvironmentBasics.builder();
		builder.manifest(manifest.getResult());
		builder.heap(heap.getResult());
		builder.releaseNotes(releaseNotes.getResult());
		builder.environment(environment.getResult());
		return builder.build();
	}

}
