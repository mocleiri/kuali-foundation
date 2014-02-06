package org.kuali.common.devops.logic.function;

import static org.kuali.common.devops.logic.Examiner.getEnvJspUrl;
import static org.kuali.common.devops.logic.Manifests.getManifestUrl;
import static org.kuali.common.devops.logic.Tomcats.getHeapUrl;
import static org.kuali.common.devops.logic.Tomcats.getReleaseNotesUrl;

import org.kuali.common.devops.model.EnvironmentBasics;
import org.kuali.common.devops.model.FileCache;

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
		FunctionRunner<String, FileCache> manifest = FunctionRunner.create(function, getManifestUrl(fqdn));
		FunctionRunner<String, FileCache> heap = FunctionRunner.create(function, getHeapUrl(fqdn));
		FunctionRunner<String, FileCache> releaseNotes = FunctionRunner.create(function, getReleaseNotesUrl(fqdn));
		FunctionRunner<String, FileCache> environment = FunctionRunner.create(function, getEnvJspUrl(fqdn));
		ConcurrentFunctions.execute(ImmutableList.of(manifest, heap, releaseNotes, environment));
		EnvironmentBasics.Builder builder = EnvironmentBasics.builder();
		builder.manifest(manifest.getResult());
		builder.heap(heap.getResult());
		builder.releaseNotes(releaseNotes.getResult());
		builder.environment(environment.getResult());
		return builder.build();
	}

}
