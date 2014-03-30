package org.kuali.common.devops.logic;

import static com.google.common.base.Stopwatch.createStarted;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.validation.ConstraintViolation;

import org.kuali.common.core.build.ViolationsBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.metadata.logic.EnvironmentMetadataService;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.inform.PercentCompleteInformer;
import org.slf4j.Logger;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class BuilderFillerCallable implements Callable<Long> {

	private final ImmutableList<Environment.Builder> builders;
	private final EnvironmentMetadataService service;
	private final PercentCompleteInformer informer;

	private static final Logger logger = newLogger();

	@Override
	public Long call() {
		Stopwatch sw = createStarted();
		for (Environment.Builder builder : builders) {
			logger.info(builder.getFqdn());
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

	public static class Builder extends ViolationsBuilder<BuilderFillerCallable> {

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
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<BuilderFillerCallable>> violations() {
			return violations(make());
		}

		private BuilderFillerCallable make() {
			return new BuilderFillerCallable(this);
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
