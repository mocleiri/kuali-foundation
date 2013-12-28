package org.kuali.common.util.spring.event;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

import com.google.common.collect.ImmutableList;

/**
 * <p>
 * Associate an executable with one or more Spring framework application events.
 * </p>
 * 
 * <p>
 * If an application event gets fired where both {@code supportsEventType} and {@code supportsSourceType} return {@code true}, {@code onApplicationEvent} invokes the executable.
 * </p>
 * 
 * <p>
 * The default behavior of {@code supportsEventType} and {@code supportsSourceType} is to always return true irrespective of what application event was fired.
 * </p>
 * 
 * <p>
 * To be more discriminatory, provide values for {@code supportedSourceTypes} and {@code supportedEventTypes}.
 * </p>
 * 
 * <p>
 * To limit execution to a specific event type, eg {@code ContextRefreshedEvent}:
 * </p>
 * 
 * <pre>
 * ExecutableApplicationListener.builder(executable).supportedEventType(ContextRefreshedEvent.class).build()
 * </pre>
 */
public final class ExecutableApplicationListener implements SmartApplicationListener {

	private static final Logger logger = LoggerUtils.make();

	private final Executable executable;
	private final int order;
	private final List<Class<?>> supportedSourceTypes;
	private final List<Class<? extends ApplicationEvent>> supportedEventTypes;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("Received event: [{}]", event.getClass().getCanonicalName());
		executable.execute();
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return supportedEventTypes.isEmpty() || supportedEventTypes.contains(eventType);
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return supportedSourceTypes.isEmpty() || supportedSourceTypes.contains(sourceType);
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
			checkNotNull(instance.getExecutable(), "executable cannot be null");
			checkNotNull(instance.getSupportedEventTypes(), "supportedEventTypes cannot be null");
			checkNotNull(instance.getSupportedSourceTypes(), "supportedSourceTypes cannot be null");
			checkArgument(ImmutableList.class.isAssignableFrom(instance.getSupportedEventTypes().getClass()), "supportedEventTypes must be immutable");
			checkArgument(ImmutableList.class.isAssignableFrom(instance.getSupportedSourceTypes().getClass()), "supportedSourceTypes must be immutable");
		}

		public Builder order(int order) {
			this.order = order;
			return this;
		}

		public Builder supportedSourceTypes(List<Class<?>> supportedSourceTypes) {
			this.supportedSourceTypes = supportedSourceTypes;
			return this;
		}

		public Builder supportedSourceType(Class<?> supportedSourceType) {
			return supportedSourceTypes(ImmutableList.<Class<?>> of(supportedSourceType));
		}

		public Builder supportedEventTypes(List<Class<? extends ApplicationEvent>> supportedEventTypes) {
			this.supportedEventTypes = supportedEventTypes;
			return this;
		}

		public Builder supportedEventType(Class<? extends ApplicationEvent> supportedEventType) {
			return supportedEventTypes(ImmutableList.<Class<? extends ApplicationEvent>> of(supportedEventType));
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
