package org.kuali.common.devops.logic;

import static com.google.common.base.Stopwatch.createStarted;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.kuali.common.devops.metadata.logic.EnvironmentMetadataService;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class BuilderFillerCallable implements Callable<Long> {

	private final String group;
	private final ImmutableList<Environment.Builder> builders;
	private final EnvironmentMetadataService service;

	@Override
	public Long call() {
		Stopwatch sw = createStarted();
		Environments2.fillIn(group, builders, service);
		return sw.elapsed(TimeUnit.MILLISECONDS);
	}

	private BuilderFillerCallable(Builder builder) {
		this.group = builder.group;
		this.builders = ImmutableList.copyOf(builder.builders);
		this.service = builder.service;
	}

	public static class Builder extends ValidatingBuilder<BuilderFillerCallable> {

		private String group;
		private List<Environment.Builder> builders;
		private EnvironmentMetadataService service;

		public Builder group(String group) {
			this.group = group;
			return this;
		}

		public Builder builders(List<Environment.Builder> builders) {
			this.builders = builders;
			return this;
		}

		public Builder service(EnvironmentMetadataService service) {
			this.service = service;
			return this;
		}

		@Override
		public BuilderFillerCallable getInstance() {
			return new BuilderFillerCallable(this);
		}

	}

	public String getGroup() {
		return group;
	}

	public List<Environment.Builder> getBuilders() {
		return builders;
	}

	public EnvironmentMetadataService getService() {
		return service;
	}

}
