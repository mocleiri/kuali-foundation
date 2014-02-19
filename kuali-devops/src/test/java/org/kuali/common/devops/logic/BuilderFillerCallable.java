package org.kuali.common.devops.logic;

import static com.google.common.base.Stopwatch.createStarted;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.List;
import java.util.concurrent.Callable;

import org.kuali.common.devops.metadata.logic.EnvironmentMetadataService;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.build.LegacyValidatingBuilder;
import org.kuali.common.util.inform.PercentCompleteInformer;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class BuilderFillerCallable implements Callable<Long> {

	private final ImmutableList<Environment.Builder> builders;
	private final EnvironmentMetadataService service;
	private final PercentCompleteInformer informer;

	@Override
	public Long call() {
		Stopwatch sw = createStarted();
		for (Environment.Builder builder : builders) {
			Environments2.fillIn(builder, service);
			informer.incrementProgress();
		}
		return sw.elapsed(MILLISECONDS);
	}

	private BuilderFillerCallable(Builder builder) {
		this.builders = ImmutableList.copyOf(builder.builders);
		this.service = builder.service;
		this.informer = builder.informer;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends LegacyValidatingBuilder<BuilderFillerCallable> {

		private List<Environment.Builder> builders;
		private EnvironmentMetadataService service;
		private PercentCompleteInformer informer;

		public Builder informer(PercentCompleteInformer informer) {
			this.informer = informer;
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
		public BuilderFillerCallable build() {
			return checkConstraints(new BuilderFillerCallable(this), validator);
		}

		public List<Environment.Builder> getBuilders() {
			return builders;
		}

		public void setBuilders(List<Environment.Builder> builders) {
			this.builders = builders;
		}

		public EnvironmentMetadataService getService() {
			return service;
		}

		public void setService(EnvironmentMetadataService service) {
			this.service = service;
		}

		public PercentCompleteInformer getInformer() {
			return informer;
		}

		public void setInformer(PercentCompleteInformer informer) {
			this.informer = informer;
		}

	}

	public List<Environment.Builder> getBuilders() {
		return builders;
	}

	public EnvironmentMetadataService getService() {
		return service;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

}
