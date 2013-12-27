package org.kuali.common.util.spring.event;

import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * <p>
 * Associate an executable with one or more application event's or source types.
 * </p>
 * 
 * <p>
 * If a supported application event or source type is received, the executable is executed.
 * </p>
 */
public final class ExecutableApplicationListener implements SmartApplicationListener {

	private static final Logger logger = LoggerUtils.make();

	private final Executable executable;
	private final int order;
	private final List<Class<?>> supportedSourceTypes;
	private final List<Class<? extends ApplicationEvent>> supportedEventTypes;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("Recieved event: [{}]", event.getClass());
		boolean supportedEventType = supportsEventType(event.getClass());
		boolean supportedSourceType = supportsSourceType(event.getSource().getClass());
		boolean execute = supportedEventType || supportedSourceType;
		if (execute) {
			executable.execute();
		}
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return supportedEventTypes.contains(eventType);
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return supportedSourceTypes.contains(sourceType);
	}

	private ExecutableApplicationListener(Builder builder) {
		this.executable = builder.executable;
		this.order = builder.order;
		this.supportedSourceTypes = builder.supportedSourceTypes;
		this.supportedEventTypes = builder.supportedEventTypes;
	}

	public static Builder builder(Executable executable) {
		return new Builder(executable);
	}

	public static class Builder {

		// Required
		private final Executable executable;

		// Optional
		private int order = 0; // Lower values mean higher priority, higher values mean lower priority
		private List<Class<?>> supportedSourceTypes = ImmutableList.of();
		private List<Class<? extends ApplicationEvent>> supportedEventTypes = ImmutableList.of();

		public Builder(Executable executable) {
			this.executable = executable;
		}

		public ExecutableApplicationListener build() {
			this.supportedEventTypes = ImmutableList.copyOf(supportedEventTypes);
			this.supportedSourceTypes = ImmutableList.copyOf(supportedSourceTypes);
			ExecutableApplicationListener instance = new ExecutableApplicationListener(this);
			validate(instance);
			return instance;
		}

		private void validate(ExecutableApplicationListener instance) {
			Preconditions.checkNotNull(executable, "executable cannot be null");
			Preconditions.checkNotNull(supportedSourceTypes, "supportedSourceTypes cannot be null");
			Preconditions.checkNotNull(supportedEventTypes, "supportedEventTypes cannot be null");
		}

		public Builder order(int order) {
			this.order = order;
			return this;
		}

		public Builder supportedSourceTypes(List<Class<?>> supportedSourceTypes) {
			this.supportedSourceTypes = supportedSourceTypes;
			return this;
		}

		public Builder supportedEventTypes(List<Class<? extends ApplicationEvent>> supportedEventTypes) {
			this.supportedEventTypes = supportedEventTypes;
			return this;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public List<Class<?>> getSupportedSourceTypes() {
			return supportedSourceTypes;
		}

		public void setSupportedSourceTypes(List<Class<?>> supportedSourceTypes) {
			this.supportedSourceTypes = supportedSourceTypes;
		}

		public List<Class<? extends ApplicationEvent>> getSupportedEventTypes() {
			return supportedEventTypes;
		}

		public void setSupportedEventTypes(List<Class<? extends ApplicationEvent>> supportedEventTypes) {
			this.supportedEventTypes = supportedEventTypes;
		}

		public Executable getExecutable() {
			return executable;
		}
	}

	public Executable getExecutable() {
		return executable;
	}

	public List<Class<?>> getSupportedSourceTypes() {
		return supportedSourceTypes;
	}

	public List<Class<? extends ApplicationEvent>> getSupportedEventTypes() {
		return supportedEventTypes;
	}

}
